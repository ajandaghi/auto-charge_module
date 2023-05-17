package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.entities.DealType;
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
    private BalanceImpl balanceImpl;

    @Autowired
    ProfileService profileService;

    @Autowired
    ParserJwt parserJwt;

    @Autowired
     private DirectDebit directDebit;



    public void checkBalance(BalanceRequest balanceRequest) {
       // System.out.println("true");
        Long balance= balanceImpl.getBalance(balanceRequest.getToken()).getBalance();
        //System.out.println(balance);

        String user=parserJwt.getAllFromToken(balanceRequest.getToken()).getSub();
        String accountNumber=parserJwt.getAllFromToken(balanceRequest.getToken()).getAccountNumber();


        if(profileService.findById(user).isEmpty()){
            System.out.println("user does not set minimum balance");
        } else{
            Long minimumBalance=profileService.findById(user).get().getMinimumBalance();
            if(minimumBalance>balance) {
                directDebit.directDebit( new DirectRequest(balanceRequest.getToken(), accountNumber, minimumBalance - balance));
            }
        }



    }

    public void checkDealType(Messages message)  {
        try {
            String token = message.getToken();
            DealType type = message.getDealType();
            if (type.equals(DealType.WITHDRAW)) {
                checkBalance(new BalanceRequest(token));
            }
        } catch (Exception e) {
            System.out.println("Not");        }
    }
}


