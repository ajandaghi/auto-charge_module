package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthorizer {

    @Autowired
    private UserAuthorization userAuthorization;

   @GetMapping("/check-validation")
    public UserValidation checkTokenValidity(@RequestHeader(name="Authorization") String token) {
       return  userAuthorization.checkTokenValidity(token);
   }
}
