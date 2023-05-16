package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:requestedURL.properties")
@Service
public class BalanceInquiry {


    @Value("${balance.inquiry.url}")
    private String balanceInquiryUrl;

    @Autowired
    ProfileService profileService;

    @Autowired
    DirectDebit directDeposit;



    private final RestTemplate restTemplate=new RestTemplate();

    public void checkBalance(BalanceRequest balanceRequest) throws Exception {
        String user = ParserJwt.getAllFromToken(balanceRequest.getToken()).getSub();
        Long minimumBalance = profileService.findById(user).get().getMinimumBalance();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",balanceRequest.getToken());


        HttpEntity<BalanceRequest> request = new HttpEntity<>(balanceRequest,headers);
        //ResponseEntity<BalanceResponse>
        try {
            ResponseEntity<BalanceResponse> balanceResponse;
            balanceResponse= restTemplate.exchange(balanceInquiryUrl, HttpMethod.POST, request, BalanceResponse.class);
            Long balance=balanceResponse.getBody().getBalance();
             if (profileService.findById(user).isEmpty()) {
                throw new ServiceException("this user has not set auto charge profile");
            } else if (balance < minimumBalance) {
                String accountNumber = ParserJwt.getAllFromToken(balanceRequest.getToken()).getAccountNumber();
                directDeposit.directDebit(new DirectRequest(balanceResponse.getHeaders().get("Authorization").get(0),accountNumber, minimumBalance - balance));
            }

        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceException("cannot get response from wallet", e, e.getStatusCode().toString());

        }
    }


}