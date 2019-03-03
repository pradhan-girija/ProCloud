package messaging;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;
import  com.example.demo1.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
public class Jmsmessageservice {
    private Destination orderqueue;
    private JmsTemplate jms;
    public Jmsmessageservice(JmsTemplate jms,Destination orderqueue)
    {
        this.jms=jms;
        this.orderqueue=orderqueue;
    }

    public void sendOrder(Order order) {

        jms.convertAndSend("tacocloud.order.queue", order,
                message -> {
                    message.setStringProperty("X_ORDER_SOURCE", "WEB");
                    return message;

                });
    }
    @GetMapping("/convertAndSend/order")
    public String convertAndSendOrder() {
        Order order = new Order();
        jms.convertAndSend("tacocloud.order.queue", order,
                this::addOrderSource);
        return "Convert and sent order";
    }
    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}
