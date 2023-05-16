package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
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

@Service
@PropertySource("classpath:requestedURL.properties")
public class DirectDebit
{
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    DepositWalletResponse restResponse;

    @Autowired
    DepositWalletService depositWallet;

    @Value("${bank.directDebit.url}")
    private String bankDirectDebitUrl;
    public void directDebit(DirectRequest directRequest) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",directRequest.getToken());
        HttpEntity<DirectRequest> request = new HttpEntity<>(directRequest,headers);

        try {
            ResponseEntity<DirectResponse> directResponse;
            directResponse=restTemplate.exchange(bankDirectDebitUrl, HttpMethod.POST, request, DirectResponse.class);

            if(directResponse.getBody().getMessage().equals("Ok")) {
                depositWallet.deposit(new DepositWalletRequest(TrackingIdGenerator.generateID(),directResponse.getHeaders().get("Authorization").get(0), directRequest.getAmount()));
            }


        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceException("cannot get response from bank", e, e.getStatusCode().toString());

        }

    }

}