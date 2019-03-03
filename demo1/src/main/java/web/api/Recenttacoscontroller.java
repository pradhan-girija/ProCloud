package web.api;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import  org.springframework.hateoas.*;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo1.*;
import Database.*;
import  org.springframework.context.annotation.*;
@RepositoryRestController
public class Recenttacoscontroller {
    IngridentsRepo repo;
    public Recenttacoscontroller(IngridentsRepo repo)
    {
        this.repo=repo;
    }
    @GetMapping(path="/tacos/recent", produces="application/hal+json")
    public ResponseEntity<Resources<Tacoresource>> recenttacos()
    {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Ingridents> tacos = repo.findall(page).getContent();
        List<Tacoresource> tacoResources =
                new Tacoresourceassembler().toResources(tacos);
        Resources<Tacoresource> recentResources =
                new Resources<Tacoresource>(tacoResources);
        recentResources.add(
                linkTo(methodOn(Recenttacoscontroller.class).recenttacos())
                        .withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);

    }
    @Bean
    public ResourceProcessor<PagedResources<Resource<Ingridents>>> tacoProcessor(EntityLinks links) {
        return new ResourceProcessor<PagedResources<Resource<Ingridents>>>() {
            @Override
            public PagedResources<Resource<Ingridents>> process(
                    PagedResources<Resource<Ingridents>> resource) {
                resource.add(
                        links.linkFor(Ingridents.class)
                                .slash("recent")
                                .withRel("recents"));
                return resource;
            }
        };
    }
}
