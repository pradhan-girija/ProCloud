package messaging;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.*;
import org.springframework.jms.support.converter.*;
import  com.example.demo1.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class JmsKitchenRecieveservice {

    private JmsTemplate jms;
    @Autowired
    public JmsKitchenRecieveservice(JmsTemplate jms) {
        this.jms = jms;
    }
    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}



