package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.security.JWTRequest;
import by.gurinovich.travelcompanionsearch.security.JWTResponse;
import by.gurinovich.travelcompanionsearch.service.AuthService;
import by.gurinovich.travelcompanionsearch.service.MailService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import by.gurinovich.travelcompanionsearch.util.validator.UserDTOValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController{
    private final AuthService authService;
    private final UserService userService;
    private final UserDTOValidator userValidator;
    private final UserMapper userMapper;
    private final MailService mailService;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest jwtRequest){
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        userValidator.validate(userDTO, bindingResult);
        User user = userMapper.fromDTO(userDTO);
        userService.save(user);
        mailService.sendRegistrationEmailMessage(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/enable")
    public ResponseEntity<Object> enableUser(@RequestBody UserDTO userDTO){
        userService.enable(userDTO);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping("/enable/{user_id}/resend")
    @ResponseBody
    public void resendConfirmationCode(@PathVariable("user_id") UUID userId){
        User user = userService.getById(userId);
        userService.updateConfirmationCode(user);
        mailService.sendRegistrationEmailMessage(user);
    }

    @GetMapping("/reset")
    public HttpStatus sendResetLetter(@RequestParam("email") String email){
        User user = userService.getByEmail(email);
        mailService.sendPasswordResetMessage(user);
        return HttpStatus.OK;
    }

    @GetMapping("/reset/password")
    public HttpStatus sendResetLetter(@RequestParam("id") UUID userId,
            @RequestParam("password") String password){
        User user = userService.getById(userId);
        userService.updatePassword(user, password);
        return HttpStatus.OK;
    }

}