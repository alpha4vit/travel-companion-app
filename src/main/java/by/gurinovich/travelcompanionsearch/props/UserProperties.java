package by.gurinovich.travelcompanionsearch.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user")
@Getter
@Setter
public class UserProperties {
    private String avatarName;
}
