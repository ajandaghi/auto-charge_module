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

import java.net.http.HttpResponse;

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
        BalanceResponse balanceResponse = balanceImpl.getBalance(balanceRequest.getToken(),balanceRequest);
        //System.out.println(balance);
        Long balance=balanceResponse.getBalance();
        String accountNumber = parserJwt.getAllFromToken(balanceRequest.getToken()).getAccountNumber().substring(0, 10);
       // System.out.println(balance);
        if (profileService.findByWalletId(balanceRequest.getWalletId()) == null) {
            System.out.println("user does not set minimum balance");
        } else {
            Long minimumBalance = profileService.findByWalletId(balanceRequest.getWalletId()).getMinimumBalance();
            if (minimumBalance > balance) {
                directDebit(new DirectRequest(balanceRequest.getWalletId(),balanceRequest.getToken(), accountNumber, "1234567890", minimumBalance - balance));
            }
        }

    }

    public void directDebit(DirectRequest directRequest) {


        DirectResponse response = directImpl.directDebit(directRequest.getToken(), directRequest);
       // System.out.println(directRequest.getToken());

        if (response.getMessage().equals("success transaction")) {

            depositToWallet(new DepositWalletRequest(directRequest.getWalletId(),TrackingIdGenerator.generateID(), directRequest.getToken(), directRequest.getAmount()));

        }


    }

    public void checkDealType(Messages message) {

        String token = message.getToken();
        DealType type = message.getDealType();
        if (type.equals(DealType.WITHDRAW)) {
            checkBalance(new BalanceRequest(message.getWalletId(), token));
        }

    }

    public void depositToWallet(DepositWalletRequest depositWalletRequest) {


        TransactionDto transactionDto = TransactionDto.builder()
                .amount(depositWalletRequest.getAmount())
                .user(parserJwt.getAllFromToken(depositWalletRequest.getToken()).getSub())
                .trackingId(TrackingIdGenerator.generateID()).build();
        transactionDto.setWalletId(depositWalletRequest.getWalletId());


        DepositWalletResponse walletResponse = depositImpl.depositToWallet(depositWalletRequest.getToken(), depositWalletRequest);
        transactionDto.setStatus(walletResponse.getStatus());

        transactionService.add(transactionConverter.convertDto(transactionDto));


    }

    public void retryDeposit(DepositWalletRequest depositWalletRequest) throws Exception {

        for (int i = 0; i < 3; i++) {
            DepositWalletResponse walletResponse = depositImpl.depositToWallet(depositWalletRequest.getToken(), depositWalletRequest);

            if (walletResponse.getStatus().equals(TransactionStatus.SUCCESS)) {
                TransactionEntity trans = transactionService.findByTrackingId(depositWalletRequest.getTrackingId());
                trans.setStatus(TransactionStatus.SUCCESS);
                transactionService.update(trans);
                break;

            }


        }
    }
}
