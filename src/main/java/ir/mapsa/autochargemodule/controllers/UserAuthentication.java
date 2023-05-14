package ir.mapsa.autochargemodule.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication",url = "192.168.43.12:8081")
public interface UserAuthentication {
    @GetMapping("/api/token/is-valid")
    Object checkTokenValidity(@RequestHeader(name="Authorization") String token);

}
