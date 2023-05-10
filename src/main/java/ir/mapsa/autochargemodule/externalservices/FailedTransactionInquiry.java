package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.models.dtos.TransactionDto;
import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import ir.mapsa.autochargemodule.services.TransactionService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:requestedURL.properties")
public class FailedTransactionInquiry {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    WalletResponse walletResponse;
    @Value("${failed.inquiry.url}")
    private String failedInquiryUrl;

      @Autowired
      TransactionService transactionService;

    @Autowired
    TransactionConverter transactionConverter;

   // @Scheduled(cron = "*/5 * * * * *")
    public void checkFailedTransactions(String trackingId, TransactionDto transactionDto) throws Exception {

        JSONObject object = new JSONObject();
        object.put("trackingId", trackingId);
        HttpEntity<JSONObject> request = new HttpEntity<>(object);

        walletResponse= restTemplate.postForEntity(failedInquiryUrl, request, WalletResponse.class).getBody();

        if(walletResponse!=null && walletResponse.getStatus().equals(TransactionStatus.SUCCEEDED)){
            transactionService.update(transactionConverter.convertDto(transactionDto));
        }
    }

}