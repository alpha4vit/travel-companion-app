package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(
                userMapper.toDTOs(userService.getAll()),
                HttpStatus.OK);
    }
}
