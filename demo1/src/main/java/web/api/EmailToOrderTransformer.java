package web.api;

import com.example.demo1.Order;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

public class EmailToOrderTransformer {
    @Component
    public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {
        @Override
        protected AbstractIntegrationMessageBuilder<Order>  doTransform(Message mailMessage) throws Exception {
            Order tacoOrder = processPayload(mailMessage);
            return MessageBuilder.withPayload(tacoOrder);
        }
    }


}
