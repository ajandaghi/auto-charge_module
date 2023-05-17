package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.DealType;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import ir.mapsa.autochargemodule.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class AutoChargeService {

    @Autowired
    private BalanceImpl balanceImpl;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private DirectImpl directImpl;

    @Autowired
    private ParserJwt parserJwt;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DepositImpl depositImpl;

    @Autowired
    private TransactionConverter transactionConverter;

    public void checkBalance(BalanceRequest balanceRequest) {
        // System.out.println("true");
        Long balance= balanceImpl.getBalance(balanceRequest.getToken()).getBalance();
        //System.out.println(balance);

        String user=parserJwt.getAllFromToken(balanceRequest.getToken()).getSub();
        String accountNumber=parserJwt.getAllFromToken(balanceRequest.getToken()).getAccountNumber();


        if(profileService.findById(user).isEmpty()){
            System.out.println("user does not set minimum balance");
        } else{
            Long minimumBalance=profileService.findById(user).get().getMinimumBalance();
            if(minimumBalance>balance) {
                directDebit( new DirectRequest(balanceRequest.getToken(), accountNumber, minimumBalance - balance));
            }
        }

    }

    public void directDebit(DirectRequest directRequest) {


        DirectResponse response = directImpl.directDebit(directRequest.getToken(), directRequest);
        if (response.getMessage().equals("ACCEPTED")) {
            try {
                depositToWallet( new DepositWalletRequest(TrackingIdGenerator.generateID(), directRequest.getToken(), directRequest.getAmount()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void checkDealType(Messages message) {

        String token = message.getToken();
        DealType type = message.getDealType();
        if (type.equals(DealType.WITHDRAW)) {
            checkBalance(new BalanceRequest(token));
        }

    }

    public void depositToWallet(DepositWalletRequest depositWalletRequest) throws Exception {


        TransactionDto transactionDto=TransactionDto.builder()
                .amount(depositWalletRequest.getAmount())
                .trackingId(TrackingIdGenerator.generateID()).build();
        transactionDto.setUser(parserJwt.getAllFromToken(depositWalletRequest.getToken()).getSub());

        try{
            DepositWalletResponse walletResponse=depositImpl.depositToWallet(depositWalletRequest.getToken(),depositWalletRequest);
            transactionDto.setStatus(walletResponse.getStatus());

        } catch (HttpServerErrorException | HttpClientErrorException e) {

            transactionDto.setStatus(TransactionStatus.TIMEOUT);

            throw new ServiceException("cannot get response from wallet",e,e.getStatusCode().toString());
        } finally {
            transactionService.add(transactionConverter.convertDto(transactionDto));

        }

    }

    public void retryDeposit(DepositWalletRequest depositWalletRequest) throws Exception{

        for(int i=0;i<3;i++) {
            try {
                DepositWalletResponse walletResponse=depositImpl.depositToWallet(depositWalletRequest.getToken(),depositWalletRequest);

                if(walletResponse.getStatus().equals(TransactionStatus.SUCCESS)){
                    TransactionEntity trans=  transactionService.findByTrackingId(depositWalletRequest.getTrackingId());
                    trans.setStatus(TransactionStatus.SUCCESS);
                    transactionService.update(trans);
                    break;

                }

            } catch (HttpServerErrorException | HttpClientErrorException e) {
                throw new ServiceException("cannot get response from wallet/TimeOut", e, e.getStatusCode().toString());
            }
        }
    }
}
