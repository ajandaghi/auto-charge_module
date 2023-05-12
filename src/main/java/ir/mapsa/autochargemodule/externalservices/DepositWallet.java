package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import ir.mapsa.autochargemodule.services.TransactionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")
@Service
public class DepositWallet {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TrackingIdGenerator trackingIdGenerator;

    @Autowired
    RestResponse restResponse;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    FailedTransactionInquiry failedTransactionInquiry;

    @Value("${wallet.deposit.url}")
    private String walletDepositUrl;

    public void Deposit(String walletId, Long amount, String trackingId) throws Exception {
        //ResponseEntity<BalanceResponse>
        JSONObject object = new JSONObject();
        object.put("trackingId",trackingIdGenerator.generateID());
        object.put("walletId", walletId);
        object.put("amount", amount);
        HttpEntity<JSONObject> request = new HttpEntity<>(object);
        restResponse =restTemplate.postForEntity(walletDepositUrl, request, RestResponse.class).getBody();

        if(restResponse ==null) {
            throw new RuntimeException("Null Response");
        }
        TransactionDto transactionDto=TransactionDto.builder()
                .status(restResponse.getStatus())
                .amount(amount)
                .trackingId(trackingId).build();
        transactionDto.setWalletId(walletId);
        transactionService.add(transactionConverter.convertDto(transactionDto));


          if(restResponse.getStatus().equals(TransactionStatus.FAILED)){
              Thread.sleep(7000);
              failedTransactionInquiry.checkFailedTransactions(trackingId,transactionDto);
         }
    }
}