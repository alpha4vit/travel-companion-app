package by.gurinovich.travelcompanionsearch.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.mail")
@Getter
@Setter
public class MailProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String resetLink;
}
