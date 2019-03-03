package messaging;
import java.util.*;
import com.example.demo1.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
public class Rabbitmessagesender {

    private RabbitTemplate rabbit;
    @Autowired
    public Rabbitmessagesender(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    public void sendOrder(Order order) {
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties props = new MessageProperties();
        Message message = converter.toMessage(order, props);
        rabbit.send("tacocloud.order", message);
    }

    @Override
    public void sendOrder(Order order) {
        rabbit.convertAndSend("tacocloud.order.queue", order,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message)
                            throws AmqpException {
                        MessageProperties props = message.getMessageProperties();
                        props.setHeader("X_ORDER_SOURCE", "WEB");
                        return message;
                    }
                });
    }
}
