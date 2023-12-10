package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.security.JWTRequest;
import by.gurinovich.travelcompanionsearch.security.JWTResponse;
import by.gurinovich.travelcompanionsearch.service.AuthService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController{
    private final AuthService authService;
    private final UserService userService;
    //private final UserDTOValidator userValidator;
    private final UserMapper userMapper;
    //private final EmailService emailService;

    @PostMapping("/login")
    public JWTResponse login(@RequestBody JWTRequest jwtRequest){
        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO userDTO, BindingResult bindingResult){
//        userValidator.validate(userDTO, bindingResult);
//        checkBindingResult(bindingResult);
        User user = userMapper.fromDTO(userDTO);
        userService.save(user);
        //emailService.sendEmailMessage(user, EmailType.REGISTRATION, new Properties());
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
    }

//    @PostMapping("/enable")
//    public ResponseEntity<Object> enableUser(@RequestBody UserDTO userDTO){
//        userService.enable(userDTO.getEmail(), userDTO.getConfirmationCode());
//        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
//    }

}