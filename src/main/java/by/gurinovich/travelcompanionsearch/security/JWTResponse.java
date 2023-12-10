package by.gurinovich.travelcompanionsearch.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public final class JWTResponse {
    private UUID id;
    private String username;
    private String accessToken;
    private String refreshToken;
}