package devgraft;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {

    @GetMapping("home")
    public String home() {
        return "Well Come Devgraft!\nmonolithic-boilerplate";
    }
}
