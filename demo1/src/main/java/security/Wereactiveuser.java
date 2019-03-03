package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public class Wereactiveuser {

    @Autowired
    Userrepository userRepo;
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        User user = userRepo.findByUsername(username)
                        if (user == null) {
                            throw new UsernameNotFoundException(
                                    username " + not found")
                        }
                        return user.toUserDetails();
                    }
                });
    }
    @Service
    public ReactiveUserDetailsService userDetailsService(
            Userrepository userRepo) {
        return new ReactiveUserDetailsService() {
            @Override
            public Mono<UserDetails> findByUsername(String username) {
                return userRepo.findByUsername(username).map(user -> {
                            return user.toUserDetails();
                        });
            }
        };
    }
}
