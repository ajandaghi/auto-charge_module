package ir.mapsa.autochargemodule.externalservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")

public class BalanceInquiry {


    @Value("${balance.inquiry.url}")
    private String balanceInquiryUrl;



    private final RestTemplate restTemplate=new RestTemplate();

    public BalanceResponse checkBalance(String walletId) throws JSONException {
   //ResponseEntity<BalanceResponse>
        JSONObject object=new JSONObject();
        object.put("walletId",walletId);
        HttpEntity<JSONObject> request=new HttpEntity<>(object);
       return restTemplate.postForEntity(balanceInquiryUrl,request,BalanceResponse.class).getBody();
    }
}