package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "depositing",url = "${wallet.host.ip}" )


public interface Depositing {

    @PostMapping ( "${wallet.deposit.path}")
    DepositWalletResponse deposit(@RequestHeader(name="Authorization") String token, @RequestBody DepositWalletRequest depositWalletRequest);
}
