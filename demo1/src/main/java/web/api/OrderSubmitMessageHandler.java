package web.api;

import com.example.demo1.Order;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class OrderSubmitMessageHandler implements GenericHandler<Order> {

    private RestTemplate rest;
    private ApiProperties apiProps;
    public OrderSubmitMessageHandler(
            ApiProperties apiProps, RestTemplate rest) {
        this.apiProps = apiProps;
        this.rest = rest;
    }
    @Override
    public Object handle(Order order, Map<String, Object> headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }
}
