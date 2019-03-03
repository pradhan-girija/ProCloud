package com.example.demo1;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import com.example.demo1.Ingridents.Type;
import Database.IngridentsRepo;
import web.api.Designtacocontroller;
public class Testtacocontroller {

    @Test
    public void shouldReturnRecentTacos() {
        Ingridents[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L)};
        Flux<Taco> tacoFlux = Flux.just(tacos);
        TacoRepository tacoRepo = Mockito.mock(TacoRepository.class);
        when(tacoRepo.findAll()).thenReturn(tacoFlux);
        WebTestClient testClient = WebTestClient.bindToController(
                new DesignTacoController(tacoRepo))
                .build();
        testClient.get().uri("/design/recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[0].name").isEqualTo("Taco 1").jsonPath("$[1].id")
                .isEqualTo(tacos[1].getId().toString()).jsonPath("$[1].name")
                .isEqualTo("Taco 2").jsonPath("$[11].id")
                .isEqualTo(tacos[11].getId().toString())
        jsonPath("$[11].name").isEqualTo("Taco 12").jsonPath("$[12]")
                .doesNotExist();
 .jsonPath("$[12]").doesNotExist();
    }
    private Ingridents testTaco(Long number) {
        Ingridents taco = new Ingridents();
        taco.setId(UUID.randomUUID());
        taco.setName("Taco " + number);
        List<Ingridents> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingridents("INGA", "Ingredient A", Type.WRAP));
        ingredients.add(
                new Ingridents("INGB", "Ingredient B", Type.PROTEIN));
        taco.(ingredients);
        return taco;
    }
}

