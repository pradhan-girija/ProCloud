package web.api;
import com.example.demo1.*;
import controller.Tacocontroller;
import org.springframework.hateoas.mvc.*;
public class Ingridentsresourceassembler extends ResourceAssemblerSupport<Ingridents,Ingredientresource> {

    public Ingridentsresourceassembler() {
        super(Tacocontroller.class, Ingredientresource.class);
    }
    @Override
    public Ingredientresource toResource(Ingridents ingredient) {
        return createResourceWithId(ingredient.getId(), ingredient);
    }
    @Override
    protected Ingredientresource instantiateResource(
            Ingridents ingredient) {
        return new Ingredientresource(ingredient);
    }
}
