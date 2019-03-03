package web.api;
import java.util.*;
import com.example.demo1.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import Database.IngridentsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.*;
import controller.Tacocontroller;
import com.example.demo1.Ingridents;
import reactor.core.publisher.Flux;

//handle request for design
@RestController
@RequestMapping(    path="/design",produces = "/application/json")


public class Designtacocontroller {

    private IngridentsRepo tacoRepo;
    @Autowired
    EntityLinks entityLinks;
    public Designtacocontroller(IngridentsRepo tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    @GetMapping("/recent")
    public Flux<Ingridents> recenttacos() {
        return Flux.fromIterable(tacoRepo.findall()).take(12);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingridents> tacoById(@PathVariable("id") Long id) {
        Optional<Ingridents> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/recent")
    public Resources<Resource<Ingridents>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Ingridents> tacos = tacoRepo.findall(page).getContent();
        Resources<Resource<Ingridents>> recentResources = Resources.wrap(tacos);
        recentResources.add(
                new Link("http://localhost:8080/design/recent", "recents"));
        return recentResources;
    }
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingridents postTaco(@RequestBody Ingridents taco) {
        return tacoRepo.save(taco);
    }
    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return tacoRepo.save(order);
    }
    @DeleteMapping("/{orderId}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            tacoRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        Order order = tacoRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return tacoRepo.save(order);
    }



}




