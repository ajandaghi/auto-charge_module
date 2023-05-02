package ir.mapsa.autochargemodule.externalservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class BalanceInquiry {

    private final RestTemplate restTemplate=new RestTemplate();

    public BalanceResponse checkBalance(String walletId) throws JSONException {
   //ResponseEntity<BalanceResponse>
        JSONObject object=new JSONObject();
        object.put("walletId",walletId);
        HttpEntity<JSONObject> request=new HttpEntity<>(object);
       return restTemplate.postForEntity("http://localhost:8080/wallet/balance",request,BalanceResponse.class).getBody();
    }
}