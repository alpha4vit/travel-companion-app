package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.security.JWTRequest;
import by.gurinovich.travelcompanionsearch.security.JWTResponse;
import by.gurinovich.travelcompanionsearch.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class  AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    public JWTResponse login(JWTRequest jwtRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                        jwtRequest.getPassword()));
        User user = userService.getByUsername(jwtRequest.getUsername());
        return JWTResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .accessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()))
                .refreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()))
                .build();

    }

    public JWTResponse register(User user){
        User created = userService.save(user);
        return JWTResponse.builder()
                .id(created.getId())
                .username(created.getUsername())
                .accessToken(jwtTokenProvider.createAccessToken(created.getId(), created.getUsername(), created.getRoles()))
                .refreshToken(jwtTokenProvider.createRefreshToken(created.getId(), created.getUsername()))
                .build();
    }

    public JWTResponse refresh(String token){
        return jwtTokenProvider.refreshUserTokens(token);
    }
}
