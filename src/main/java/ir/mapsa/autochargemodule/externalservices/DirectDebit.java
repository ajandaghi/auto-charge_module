package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.services.TrackingIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:requestedURL.properties")
public class DirectDebit {


    @Autowired
    private DepositWalletService depositWalletService;

    @Autowired
    private DirectImpl directImpl;



    @Value("${bank.directDebit.url}")
    private String bankDirectDebitUrl;

    public void directDebit(DirectRequest directRequest) {


        DirectResponse response = directImpl.directDebit(directRequest.getToken(), directRequest);
        if (response.getMessage().equals("ACCEPTED")) {
            try {
                depositWalletService.depositToWallet( new DepositWalletRequest(TrackingIdGenerator.generateID(), directRequest.getToken(), directRequest.getAmount()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }


}

