package web.api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.*;
import com.example.demo1.*;
import java.util.*;
import org.springframework.http.ResponseEntity;
public class Restcontroller {

    RestTemplate rest=new RestTemplate();
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
    public Ingridents getIngredientById(String ingredientId) {
        Map<String,String> urlvariables=new HashMap<>();
        urlvariables.put("id",ingredientId);
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingridents.class, urlvariables);
    }
    public void updateIngredient(Ingridents ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient,
                ingredient.getId());

    }
    public void deleteingrident(Ingridents ingrident)
    {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingrident,
                ingrident.getId());
    }

    public Ingridents createIngredient(Ingridents ingredient) {
        ResponseEntity<Ingridents> responseEntity =
                rest.postForEntity("http://localhost:8080/ingredients",
                        ingredient,
                        Ingridents.class);
        log.info("New resource created at " +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }


}
