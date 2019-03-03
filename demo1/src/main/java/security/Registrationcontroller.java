package security;
import org.springframework.security.crypto.password.PasswordEncoder;
import  org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@RequestMapping("/register")
public class Registrationcontroller {
    private Userrepository userRepo;
    private PasswordEncoder passwordEncoder;
    public Registrationcontroller(
            Userrepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
