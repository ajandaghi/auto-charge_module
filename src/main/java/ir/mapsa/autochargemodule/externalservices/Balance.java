package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "walletBalance",url = "192.168.200.166:8080" )
public interface Balance {
        @RequestMapping (value = "/wallet/balance", method = RequestMethod.POST)
        BalanceResponse getBalance(@RequestHeader(name="Authorization") String token);
    }

