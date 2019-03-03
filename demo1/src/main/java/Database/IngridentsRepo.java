package Database;
import com.example.demo1.Ingridents;
import com.example.demo1.Order;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.*;
public interface IngridentsRepo   extends ReactiveCrudRepository<Ingridents,Long> {
    Iterable<Ingridents> findall();
    Ingridents findone(String id);
    Ingridents findbyId(Long id);
    Ingridents deletebyId(Long id);
    Ingridents save(Mono<Ingridents> ingrident);
    Order save(Order order);
}
