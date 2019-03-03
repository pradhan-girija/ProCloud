package security;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface Userrepository  extends ReactiveCassandraRepository<Userentity, UUID>{
    Mono<Userentity> findByUsername(String username);

}
