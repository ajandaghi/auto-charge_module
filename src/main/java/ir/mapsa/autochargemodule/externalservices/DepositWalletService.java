package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import ir.mapsa.autochargemodule.services.TransactionService;
import jakarta.servlet.ServletException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
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

    @Autowired
    private TimeoutTransactionAspect failedTransactionInquiry;

    @Value("${wallet.deposit.url}")
    private String walletDepositUrl;

    public void deposit(DepositWalletRequest depositWalletRequest) throws Exception {


        try{
            walletResponse =restTemplate.postForEntity(walletDepositUrl, depositWalletRequest, DepositWalletResponse.class).getBody();

        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceException("cannot get response from wallet",e,e.getStatusCode().toString());
        }


        TransactionDto transactionDto=TransactionDto.builder()
                .status(walletResponse.getStatus())
                .amount(depositWalletRequest.getAmount())
                .trackingId(TrackingIdGenerator.generateID()).build();
        transactionDto.setUser(ParserJwt.getAllFromToken(depositWalletRequest.getToken()).getSub());
        transactionService.add(transactionConverter.convertDto(transactionDto));



    }
}