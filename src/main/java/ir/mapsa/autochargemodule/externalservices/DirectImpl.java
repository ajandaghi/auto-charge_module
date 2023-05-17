package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class DirectImpl {


        @Autowired
        private Direct directBank;

        @GetMapping()
        public DirectResponse directDebit(@RequestHeader(name = "Authorization") String token, @RequestBody DirectRequest directRequest) {
            return directBank.directDebitBank(token,directRequest);
        }
    }

