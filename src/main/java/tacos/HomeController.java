package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller is a specialised version of @Component. It identifies this class as a component for Component Scanning.
// Spring's Component Scanning automatically discovers it and creates an instance of HomeController as a bean in the
// Spring Application Context.
@Controller
public class HomeController {

    // @GetMapping indicates that if an HTTP GET request is received for the root path /, then this method should handle
    // that request.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
