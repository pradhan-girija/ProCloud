package security;
import org.springframework.security.core.userdetails.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class Userdetailservice implements UserDetailsService{
    private Userrepository userRepo;
    @Autowired
    public Userdetailservice(Userrepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Userentity user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}
