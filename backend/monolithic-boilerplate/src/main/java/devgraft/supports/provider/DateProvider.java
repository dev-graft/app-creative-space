package devgraft.supports.provider;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@Component
public class DateProvider {
    public Date now() {
        return new Date();
    }
}
