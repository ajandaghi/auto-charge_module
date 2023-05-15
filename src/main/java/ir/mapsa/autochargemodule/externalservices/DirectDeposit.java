package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:requestedURL.properties")
public class DirectDeposit
{
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    DepositWalletResponse restResponse;

    @Autowired
    DepositWalletService depositWallet;

    @Value("${bank.directDebit.url}")
    private String bankDirectDebitUrl;
    public void directDebit(DirectRequest directRequest) throws Exception {

        HttpEntity<DirectRequest> request = new HttpEntity<>(directRequest);
        try {
            if(restTemplate.postForEntity(bankDirectDebitUrl, request, DirectResponse.class).getBody().getStatus().equals("Ok")) {
                depositWallet.deposit(new DepositWalletRequest());
            }


        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceException("cannot get response from bank", e, e.getStatusCode().toString());

        }

    }

}