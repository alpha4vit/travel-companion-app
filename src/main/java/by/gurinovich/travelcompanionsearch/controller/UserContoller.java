package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok()
                .body(userMapper.toDTOs(userService.getAll()));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") UUID id){
        return ResponseEntity.ok()
                .body(userMapper.toDTO(userService.getById(id)));
    }
}
