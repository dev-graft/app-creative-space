package devgraft;

import devgraft.support.sentry.SentryMessenger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    @GetMapping("v1/home")
    public String home() {
        SentryMessenger.send(new RuntimeException("센트리 테스트입니다.222"));
        return "home_v1";
    }

    @GetMapping("v2/home")
    public String homeV2() {
        return "home_v2";
    }
}
