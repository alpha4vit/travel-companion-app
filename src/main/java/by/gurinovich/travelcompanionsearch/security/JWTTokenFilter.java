package by.gurinovich.travelcompanionsearch.security;

import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTTokenFilter extends GenericFilterBean {
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bearerToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            bearerToken = bearerToken.substring(7);
        }
        if (bearerToken != null && jwtTokenProvider.validateToken(bearerToken)){
            try{
                Authentication authentication = jwtTokenProvider.getAuthenctication(bearerToken);
                if (authentication != null){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            catch (ResourceNotFoundException ignored){

            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}