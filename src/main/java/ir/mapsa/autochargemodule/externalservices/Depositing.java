package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "depositing",url = "192.168.200.166:8080" )
public interface Depositing {
    @RequestMapping(value = "/wallet/deposit", method = RequestMethod.POST)
    DepositWalletResponse deposit(@RequestHeader(name="Authorization") String token, @RequestBody DepositWalletRequest directRequest);
}
