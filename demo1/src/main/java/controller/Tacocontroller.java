package controller;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import com.example.demo1.Ingridents;
import com.example.demo1.Ingridents.Type;
import controller.*;
import com.example.demo1.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class Tacocontroller {
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingridents> ingredients = Arrays.asList(
                new Ingridents("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingridents("COTO", "Corn Tortilla", Type.WRAP),
                new Ingridents("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingridents("CARN", "Carnitas", Type.PROTEIN),
                new Ingridents("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingridents("LETC", "Lettuce", Type.VEGGIES),
                new Ingridents("CHED", "Cheddar", Type.CHEESE),
                new Ingridents("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingridents("SLSA", "Salsa", Type.SAUCE),
                new Ingridents("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingridents.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase());
        }
        model.addAttribute("design", new Demo1Application());
        return "design";
    }
}