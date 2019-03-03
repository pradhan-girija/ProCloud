package web.api;

import com.example.demo1.Ingridents;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class IngridientSericeclient {

    public IngridientSericeclient(@LoadBalanced WebClient.Builder Webclientbuilder webclient)
    {
        this.webclient=webclient;
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webclientbuilder()

    {
        return WebClient.builder();
    }
    public Mono<Ingridents> getIngredientById(String ingredientId) {
        return wcBuilder.build()
                .get()
                .uri("http://ingredient-service/ingredients/{id}", ingredientId)
                .retrieve().bodyToMono(Ingredient.class);
    }
}
