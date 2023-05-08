package ir.mapsa.autochargemodule.externalservices;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")
public class DepositWallet {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${wallet.deposit.url}")
    private String walletDepositUrl;

    public DepositResponse checkResponse(String walletId, Long amount) throws JSONException {
        //ResponseEntity<BalanceResponse>
        JSONObject object = new JSONObject();
        object.put("walletId", walletId);
        object.put("amount", amount);
        HttpEntity<JSONObject> request = new HttpEntity<>(object);
        return restTemplate.postForEntity(walletDepositUrl, request, DepositResponse.class).getBody();
    }
}