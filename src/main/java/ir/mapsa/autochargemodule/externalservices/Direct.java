package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;


@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "directing",url = "192.168.200.166:8080" )
public interface Direct {

        @PostMapping( "/bank/account")

        DirectResponse directDebitBank(@RequestHeader(name="Authorization") String token, @RequestBody DirectRequest directRequest);



}
