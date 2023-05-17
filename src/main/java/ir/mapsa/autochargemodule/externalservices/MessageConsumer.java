package ir.mapsa.autochargemodule.externalservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mapsa.autochargemodule.configuration.MessagingConfig;
import ir.mapsa.autochargemodule.models.entities.DealType;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageConsumer implements MessageListener
{

    @Autowired
    private BalanceInquiry balanceInquiry;

    @Autowired
    private ObjectMapper objectMapper;


   @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Messages message)  {
       System.out.println(message);
       balanceInquiry.checkDealType(message);

    }



    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {
        MessageListener.super.containerAckMode(mode);
    }

    @Override
    public boolean isAsyncReplies() {
        return MessageListener.super.isAsyncReplies();
    }

    @Override
    public void onMessageBatch(List<Message> messages) {
        MessageListener.super.onMessageBatch(messages);
    }
}