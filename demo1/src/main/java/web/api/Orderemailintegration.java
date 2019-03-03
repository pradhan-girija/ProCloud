package web.api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;


@Configuration
public class Orderemailintegration {

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(
            Emailint emailProps,
            EmailToOrderTransformer emailToOrderTransformer,
            OrderSubmitMessageHandler orderSubmitHandler) {
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailProps.getImapUrl()),
                        e -> e.poller(
                                Pollers.fixedDelay(emailProps.getPollRate()))).transform(emailToOrderTransformer)
                .handle(orderSubmitHandler)
                .get();
}
