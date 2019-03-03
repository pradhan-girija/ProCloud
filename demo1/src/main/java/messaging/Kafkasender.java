package messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.*;
import com.example.demo1.*;
@Service
public class Kafkasender {
    private KafkaTemplate<String,Order> kafkatemp;
    public Kafkasender(KafkaTemplate<String ,Order> kafkatemp)
    {
        this.kafkatemp=kafkatemp;
    }
    public void sendorder(Order order)
    {
        kafkatemp.send("tacocloud.orders.topic", order);
    }
}
