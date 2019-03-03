package web.api;
import java.util.*;
import org.springframework.hateoas.ResourceSupport;
import lombok.*;
import com.example.demo1.*;
import Database.*;

public class Tacoresource extends  ResourceSupport {

    private  static  final Ingridentsresourceassembler ingridentresource=new Ingridentsresourceassembler();
    @Getter
    private String name;
    @Getter
    private Date createdAt;
    @Getter
    private List<Ingridents> ingredients;

    public Tacoresource(Order taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreated();
        this.ingredients = ingridentresource.toResource(taco.getIngridents());
    }
}