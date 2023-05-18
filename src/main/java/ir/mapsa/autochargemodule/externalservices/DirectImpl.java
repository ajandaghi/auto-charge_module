package ir.mapsa.autochargemodule.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

    public class DirectImpl {


        @Autowired
        private Direct directBank;

        @PostMapping("/auto-charge/bank/direct")
        public DirectResponse directDebit(@RequestHeader(name = "Authorization") String token, DirectRequest directRequest) {
            return directBank.directDebitBank(token,directRequest);
        }
    }

