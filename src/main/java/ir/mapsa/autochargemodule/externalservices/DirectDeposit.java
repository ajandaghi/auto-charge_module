package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:requestedURL.properties")
public class DirectDeposit
{
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private RestResponse restResponse;

    @Autowired
    private DepositWalletService depositWallet;

    @Value("${bank.directDebit.url}")
    private String bankDirectDebitUrl;
    public void directDebit(String walletId,String accountNumber,Long amount) throws Exception {
        JSONObject object = new JSONObject();
        object.put("accountNumber", accountNumber);
        object.put("amount", amount);
        HttpEntity<JSONObject> request = new HttpEntity<>(object);
        restResponse =restTemplate.postForEntity(bankDirectDebitUrl, request, RestResponse.class).getBody();

        if(restResponse==null){
            throw new RuntimeException("Null Response");
        }
        if(restResponse.getStatus().equals(TransactionStatus.SUCCEEDED)){
            depositWallet.Deposit(walletId,amount, TrackingIdGenerator.generateID());
        }

    }

}