package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DepositImpl {
    @Autowired
    private Depositing walletDeposit;

    @GetMapping()
    public DepositWalletResponse depositToWallet(@RequestHeader(name = "Authorization") String token, @RequestBody DepositWalletRequest depositWalletRequest) {
        return walletDeposit.deposit(token,depositWalletRequest);
    }

}
