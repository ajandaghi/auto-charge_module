package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@PropertySource("classpath:requestedURL.properties")
@FeignClient(name = "authentication",url = "${oauth.host.ip}")

public interface UserAuthorization {
    @PostMapping("/api/token/is-valid")
    UserValidation checkTokenValidity(@RequestHeader(name="Authorization") String token);
}
