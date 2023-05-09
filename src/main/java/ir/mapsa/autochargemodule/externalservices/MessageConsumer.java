package ir.mapsa.autochargemodule.externalservices;

import org.springframework.stereotype.Service;

@Service
public class MessageConsumer
{
   // @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(String walletId) {
        System.out.println("Message received from queue : " + walletId);
    }
}