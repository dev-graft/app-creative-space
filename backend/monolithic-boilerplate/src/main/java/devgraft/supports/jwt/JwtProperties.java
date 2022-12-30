package devgraft.supports.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.jwt")
@Configuration
@Getter @Setter
public class JwtProperties {
    private String secretKey;
}
