package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import ir.mapsa.autochargemodule.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@PropertySource("classpath:requestedURL.properties")
@Service
public class DepositWalletService {


@Autowired
private ParserJwt parserJwt;


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private DepositImpl depositWallet;

    @Value("${wallet.deposit.url}")
    private String walletDepositUrl;

    public void depositToWallet(DepositWalletRequest depositWalletRequest) throws Exception {


        TransactionDto transactionDto=TransactionDto.builder()
                .amount(depositWalletRequest.getAmount())
                .trackingId(TrackingIdGenerator.generateID()).build();
        transactionDto.setUser(parserJwt.getAllFromToken(depositWalletRequest.getToken()).getSub());

        try{
           DepositWalletResponse walletResponse=depositWallet.depositToWallet(depositWalletRequest.getToken(),depositWalletRequest);
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
                DepositWalletResponse walletResponse=depositWallet.depositToWallet(depositWalletRequest.getToken(),depositWalletRequest);

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