package web.api;
import java.util.*;

import Database.IngridentsRepo;
import  org.springframework.hateoas.mvc.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import com.example.demo1.*;
import controller.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.data.domain.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class Tacoresourceassembler extends ResourceAssemblerSupport<Order,Tacoresource>{
    public IngridentsRepo tacorepo;
    public Tacoresourceassembler()
    {
        super(Tacocontroller.class,Tacoresource.class);
    }
    protected Tacoresource instantiateResource(Order order) {
        return new Tacoresource(order);
    }
    public Tacoresource toResource(Order order) {
        return createResourceWithId(order.getId(), order);
    }
    @GetMapping("/recent")
    public Resources<Tacoresource> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Ingridents> tacos = tacorepo.findall(page).getcontent();
        List<Tacoresource> tacoResources =
                new Tacoresourceassembler().toResources(tacos);
        Resources<Tacoresource> recentResources =
                new Resources<Tacoresource>(tacoResources);
        recentResources.add(
                ControllerLinkBuilder.linkTo(methodOn(Tacocontroller.class).recentacos())
                        .withRel("recents"));
        return recentResources;
    }


}
