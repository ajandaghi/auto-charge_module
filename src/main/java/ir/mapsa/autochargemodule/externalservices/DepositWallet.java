package ir.mapsa.autochargemodule.externalservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class DepositWallet {
    private final RestTemplate restTemplate = new RestTemplate();

    public DepositResponse checkResponse(String walletId, Long amount) throws JSONException {
        //ResponseEntity<BalanceResponse>
        JSONObject object = new JSONObject();
        object.put("walletId", walletId);
        object.put("amount", amount);
        HttpEntity<JSONObject> request = new HttpEntity<>(object);
        return restTemplate.postForEntity("http://localhost:8080/wallet/balance", request, DepositResponse.class).getBody();
    }
}