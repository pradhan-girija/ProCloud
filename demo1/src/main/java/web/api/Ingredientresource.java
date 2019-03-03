package web.api;
import org.springframework.hateoas.ResourceSupport;
import lombok.Getter;
import com.example.demo1.Ingridents;
import com.example.demo1.Ingridents.Type;
public class Ingredientresource extends ResourceSupport{
    @Getter
    private String name;
    @Getter
    private Type type;
    public Ingredientresource(Ingridents ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
