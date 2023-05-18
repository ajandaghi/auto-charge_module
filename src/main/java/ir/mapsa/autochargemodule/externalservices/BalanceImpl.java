package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;


@RestController
@PropertySource("classpath:requestedURL.properties")
public class BalanceImpl {



    @Autowired
    private Balance balance;

    @PostMapping ("/wallet/balance")
    public BalanceResponse getBalance(@RequestHeader(name = "Authorization") String token,BalanceRequest balanceRequest) {
        return balance.getBalance(token,balanceRequest);
    }

//    public String getHeader(){
//        return balance.returnHeader();
//    }
}

