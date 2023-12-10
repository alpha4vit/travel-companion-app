package by.gurinovich.travelcompanionsearch.service;


import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.UserRepository;
import by.gurinovich.travelcompanionsearch.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with this id not found!"));
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with this username not found!"));
    }

    @Transactional
    public User save(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new IllegalArgumentException("User with this username already exists");
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new IllegalArgumentException("User with this email already exists");
        user.setRoles(new HashSet<>(List.of(Role.ROLE_USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRandomUUID(user);
        return userRepository.save(user);
    }

    private void setRandomUUID(User user){
        UUID uuid = UUID.randomUUID();
        while (userRepository.findById(uuid).isPresent())
            uuid = UUID.randomUUID();
        user.setId(uuid);
    }
}