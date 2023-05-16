package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "authentication",url = "192.168.43.12:8081")

public interface UserAuthorization {
    @GetMapping("/api/token/is-valid")
    UserValidation checkTokenValidity(@RequestHeader(name="Authorization") String token);
}
