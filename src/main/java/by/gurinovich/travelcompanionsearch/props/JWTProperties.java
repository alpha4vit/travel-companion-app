package by.gurinovich.travelcompanionsearch.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("spring.security.jwt")
public class JWTProperties {
    private String secret;
    private Long access;
    private Long refresh;
}
