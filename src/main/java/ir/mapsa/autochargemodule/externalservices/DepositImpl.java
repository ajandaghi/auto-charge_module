package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DepositImpl {
    @Autowired
    private Depositing wd;

    @PostMapping ("/wallet/deposit")
    public DepositWalletResponse depositToWallet(@RequestHeader(name = "Authorization") String token, @RequestBody DepositWalletRequest depositWalletRequest) {
        return wd.deposit(token,depositWalletRequest);
    }

}
