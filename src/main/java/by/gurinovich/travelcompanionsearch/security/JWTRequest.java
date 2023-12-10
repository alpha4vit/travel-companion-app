package by.gurinovich.travelcompanionsearch.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTRequest {
    private String username;
    private String password;
}
