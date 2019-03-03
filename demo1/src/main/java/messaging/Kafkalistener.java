package messaging;

import com.example.demo1.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class Kafkalistener {
    private KitchenUI ui;
    @Autowired
    public Kafkalistener(KitchenUI ui) {
        this.ui = ui;
    }
    @KafkaListener(topics="tacocloud.orders.topic")
    public void handle(Order order) {
        ui.displayOrder(order);
    }

    @KafkaListener(topics="tacocloud.orders.topic")
    public void handle(Order order, Message<Order> message) {
        MessageHeaders headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}",
                headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
                headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
        ui.displayOrder(order);
    }
}
