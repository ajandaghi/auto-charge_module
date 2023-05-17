package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

    public class DirectImpl {


        @Autowired
        private Direct directBank;

        @PostMapping("/bank/account")
        public DirectResponse directDebit(@RequestHeader(name = "Authorization") String token, @RequestBody DirectRequest directRequest) {
            return directBank.directDebitBank(token,directRequest);
        }
    }

