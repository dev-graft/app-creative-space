package devgraft.bar.api;

import devgraft.bar.app.GrpcBarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BarApi {
    private final GrpcBarService grpcBarService;

    @GetMapping("{message}")
    public String message(@PathVariable String message) {
        return grpcBarService.sendMessage(message);
    }
}
