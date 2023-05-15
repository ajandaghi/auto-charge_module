package ir.mapsa.autochargemodule.controllers;

import ir.mapsa.autochargemodule.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserAuthentication userAuthentication;

   @GetMapping("/check-validation")
    public Object checkTokenValidity(@RequestHeader(name="Authorization") String token)  {
       return  userAuthentication.checkTokenValidity(token);
   }
}
