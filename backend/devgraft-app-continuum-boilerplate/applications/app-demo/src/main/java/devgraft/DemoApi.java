package devgraft;

import devgraft.support.sentry.SentryMessenger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    @GetMapping("home")
    public String home() {
        return "Well Come Devgraft! / app-continuum-boilderplate";
    }
}
