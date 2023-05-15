package ir.mapsa.autochargemodule.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication",url = "192.168.43.12:8081")
public interface UserAuthorization {
    @GetMapping("/api/token/is-valid")
    UserValidation checkTokenValidity(@RequestHeader(name="Authorization") String token);

}
