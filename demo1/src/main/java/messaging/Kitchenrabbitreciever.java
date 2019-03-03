package messaging;
import java.util.*;
import com.example.demo1.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kitchenrabbitreciever {
    public  RabbitTemplate rabbit;
    public Kitchenrabbitreciever(RabbitTemplate rabbit)
    {
        this.rabbit=rabbit;
    }
    public Order receiveOrder() {
        Message message = rabbit.receive("tacocloud.orders");
        return message != null
                ? (Order) converter.fromMessage(message)
                : null;
    }
}
