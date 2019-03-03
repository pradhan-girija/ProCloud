package web.api;

import com.example.demo1.Ingridents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Webclienttaco {
    @Autowired
    WebClient webClient;
    @Bean
    public WebClient.RequestBodyUriSpec webClient()
    {
        return webClient.method("http://localhost:8080");
    }

    //error 404
    Mono<Ingridents> geterror(String ingridentid)
    Mono<Ingridents> ingredientMono = webClient
            .get()
            .uri("http://localhost:8080/ingredients/{id}", ingridentid)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError,
                    response -> Mono.just(new UnknownIngredientException()))
            .bodyToMono(Ingridents.class);
    //get method
    Mono<Ingridents> getingridentbyid(String ingridentid)
    {
        Mono<Ingridents> ingrident=webClient.get().uri("/ingridents/{id}",ingridentid).retrieve().bodyToMono(Ingridents.class);
        ingrident.subscribe(i->i.getId());
        return ingrident;
    }
    //post method
    Mono<Ingridents> postingridentbyid(String ingridentid)
    {
        Mono<Ingridents> ingrident=webClient.post().uri("/ingridents").body(ingridentid,Ingridents.class).retrieve().bodyToMono(Ingridents.class);
        ingrident.subscribe();
        return ingrident;
    }
    //update request
    Mono<Void> putingridentbyid(Ingridents ingrident)
    {
        Mono<Void> ingrident=webClient.put().uri("/ingridents/{id}",ingrident.getClass()).syncBody().retrieve().bodyToMono(Ingridents.class);
        ingrident.subscribe();

    }

}
