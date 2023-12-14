package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
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

    @PatchMapping("/{user_id}")
    public ResponseEntity<Object> updateUser(@PathVariable("user_id") UUID id,
                                             @RequestBody UserDTO userDTO){

        User user = userService.update(userMapper.fromDTO(userDTO), id);
        return ResponseEntity.ok()
                .body(userMapper.toDTO(user));
    }


}
