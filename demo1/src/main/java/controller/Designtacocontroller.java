package controller;
import Database.*;
import com.example.demo1.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping(path="/design",produces = "application/json")
@CrossOrigin(origins = "*")
public class Designtacocontroller {
    IngridentsRepo tacorepo;
    @GetMapping("/recent")
    public Flux<Ingridents> recentTacos() {
        return Flux.fromIterable(tacorepo.findall()).take(12);
    }
    @GetMapping("/{id}")
    public Mono<Ingridents> findbyid(@PathVariable("id")  Long id)
    {
        return  tacorepo.findById(id);
    }
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Ingridents> postTaco(@RequestBody Mono<Ingridents> taco) {
        return Flux.fromIterable(tacorepo.save(taco)).next();
    }

}
