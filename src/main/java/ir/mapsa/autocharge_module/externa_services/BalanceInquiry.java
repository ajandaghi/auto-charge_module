package ir.mapsa.autocharge_module.externa_services;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BalanceInquiry {

    private final RestTemplate restTemplate=new RestTemplate();

    public BalanceResponse checkBalance(String walletId) throws JSONException {
   //ResponseEntity<BalanceResponse>
        JSONObject request=new JSONObject().put("walletId",walletId);
       return restTemplate.postForEntity("http://localhost:8080/wallet/getBalance",request,BalanceResponse.class).getBody();
    }
}