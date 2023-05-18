package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;


@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "directing",url = "${wallet.bank.ip}" )
public interface Direct {

        @PostMapping( "${bank.directDebit.path}")

        DirectResponse directDebitBank(@RequestHeader(name="Authorization") String token, @RequestBody DirectRequest directRequest);



}
