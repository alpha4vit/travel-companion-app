package by.gurinovich.travelcompanionsearch.security;

import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.util.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;


public class JWTEntityFactory {

    public static JWTEntity createJWTEntity(User user){
        return JWTEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapRoleToAuthorities(user.getRoles()))
                .email(user.getEmail())
                .build();

    }

    private static Collection<? extends GrantedAuthority> mapRoleToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

}
