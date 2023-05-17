package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BalanceImpl {


    @Autowired
    private Balance balance;

    @PostMapping ("/wallet/balance")
    public BalanceResponse getBalance(@RequestHeader(name = "Authorization") String token) {
        return balance.getBalance(token);
    }
}

