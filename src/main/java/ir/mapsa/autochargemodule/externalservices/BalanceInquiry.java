package ir.mapsa.autochargemodule.externalservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")
@Service
public class BalanceInquiry {


    @Value("${balance.inquiry.url}")
    private String balanceInquiryUrl;

    @Autowired
    ProfileService profileService;

    @Autowired
    DirectDeposit directDeposit;



    private final RestTemplate restTemplate=new RestTemplate();

    public void checkBalance(BalanceRequest balanceRequest) throws Exception {
        String user = ParserJwt.getAllFromToken(balanceRequest.getToken()).getSub();
        Long minimumBalance = profileService.findById(user).get().getMinimumBalance();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",balanceRequest.getToken());


        HttpEntity<BalanceRequest> request = new HttpEntity<>(balanceRequest,headers);
        //ResponseEntity<BalanceResponse>
        try {
            Long balance = restTemplate.postForEntity(balanceInquiryUrl, request, BalanceResponse.class).getBody().getBalance();
            if (profileService.findById(user).isEmpty()) {
                throw new ServiceException("this user has not set auto charge profile");
            } else if (balance < minimumBalance) {
                String accountNumber = ParserJwt.getAllFromToken(balanceRequest.getToken()).getAccountNumber();
                directDeposit.directDebit(new DirectRequest(balanceRequest.getToken(),accountNumber, minimumBalance - balance));
            }

        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceException("cannot get response from wallet", e, e.getStatusCode().toString());

        }
    }


}