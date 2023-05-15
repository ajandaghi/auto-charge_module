package ir.mapsa.autochargemodule.externalservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.models.entities.TransactionType;
import ir.mapsa.autochargemodule.repositories.ProfileRepository;
import ir.mapsa.autochargemodule.services.ParserJwt;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer
{
    @Autowired
    private RequestWalletId requestWalletId;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BalanceInquiry balanceInquiry;

    @Autowired
    private UserAuthorizer userAuthorizer;


   // @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Messages message) throws Exception {
        if(message.getDealType().equals(TransactionType.WITHDRAW)){
            if(userAuthorizer.checkTokenValidity(message.getToken()).getIsValid()) {
                balanceInquiry.checkBalance(new BalanceRequest(message.getToken()));
            }else{
                 throw new ServiceException(" Message with UnAuthorized token.");
            }


        }

    }
}