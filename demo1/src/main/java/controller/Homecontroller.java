package controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller   //controller
public class Homecontroller {
//handles request to root path
    @GetMapping("/")
    public  String home()
    {
        return "home";//returns the  vieww name
    }

}
