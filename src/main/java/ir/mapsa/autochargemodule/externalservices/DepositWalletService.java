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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")
@Service
public class DepositWalletService {
    private final RestTemplate restTemplate = new RestTemplate();



    @Autowired
    private DepositWalletResponse walletResponse;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionConverter transactionConverter;

    @Value("${wallet.deposit.url}")
    private String walletDepositUrl;

    public void deposit(DepositWalletRequest depositWalletRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",depositWalletRequest.getToken());
        HttpEntity<DepositWalletRequest> request = new HttpEntity<>(depositWalletRequest,headers);

        TransactionDto transactionDto=TransactionDto.builder()
                .amount(depositWalletRequest.getAmount())
                .trackingId(TrackingIdGenerator.generateID()).build();
        transactionDto.setUser(ParserJwt.getAllFromToken(depositWalletRequest.getToken()).getSub());

        try{
            ResponseEntity<DepositWalletResponse> walletResponse;
            walletResponse=restTemplate.exchange(walletDepositUrl, HttpMethod.POST, request, DepositWalletResponse.class);
            transactionDto.setStatus(walletResponse.getBody().getStatus());

        } catch (HttpServerErrorException | HttpClientErrorException e) {

            transactionDto.setStatus(TransactionStatus.TIMEOUT);

            throw new ServiceException("cannot get response from wallet",e,e.getStatusCode().toString());
        } finally {
            transactionService.add(transactionConverter.convertDto(transactionDto));

        }

    }

    public void retryDeposit(DepositWalletRequest depositWalletRequest) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",depositWalletRequest.getToken());
        HttpEntity<DepositWalletRequest> request = new HttpEntity<>(depositWalletRequest,headers);
        for(int i=0;i<3;i++) {
            try {
                ResponseEntity<DepositWalletResponse> walletResponse;
                walletResponse=restTemplate.exchange(walletDepositUrl, HttpMethod.POST, request, DepositWalletResponse.class);

                if(walletResponse.getBody().getStatus().equals(TransactionStatus.SUCCESS)){
                    TransactionEntity trans=  transactionService.findByTrackingId(depositWalletRequest.getTrackingId());
                    trans.setStatus(TransactionStatus.SUCCESS);
                    transactionService.update(trans);
                    break;

                }

            } catch (HttpServerErrorException | HttpClientErrorException e) {
                throw new ServiceException("cannot get response from wallet", e, e.getStatusCode().toString());
            }
        }
    }
}