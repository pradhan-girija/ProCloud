package security;
import  java.util.*;
import javax.persistence.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class Userentity implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
     String username;
     String password;
     String fullname;
     String street;
     String city;
     String state;
     String zip;
     String phoneNumber;
     public Userentity(){};
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
