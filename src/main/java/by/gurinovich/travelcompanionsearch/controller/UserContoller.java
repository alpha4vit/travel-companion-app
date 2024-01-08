package by.gurinovich.travelcompanionsearch.controller;


import by.gurinovich.travelcompanionsearch.dto.ImageDTO;
import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.props.UserProperties;
import by.gurinovich.travelcompanionsearch.service.ImageService;
import by.gurinovich.travelcompanionsearch.service.UserService;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.ImageMapper;
import by.gurinovich.travelcompanionsearch.util.mapper.impl.UserMapper;
import by.gurinovich.travelcompanionsearch.util.validator.UserDTOAuthValidator;
import by.gurinovich.travelcompanionsearch.util.validator.UserDTOValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final UserProperties userProperties;
    private final UserDTOValidator userDTOValidator;

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
                                             @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        userDTO.setId(id);
        userDTOValidator.validate(userDTO, bindingResult);
        User user = userService.update(userMapper.fromDTO(userDTO), id);
        return ResponseEntity.ok()
                .body(userMapper.toDTO(user));
    }

    @GetMapping("/{user_id}/avatar")
    public ResponseEntity<Object> getUserAvatar(@PathVariable("user_id") UUID userId){
        return ResponseEntity.ok()
                .body(userService.getById(userId).getAvatar());
    }

    @PostMapping("/{user_id}/avatar")
    public ResponseEntity<Object> setUserAvatar(@PathVariable("user_id") UUID userId,
                                                @RequestPart("avatar") MultipartFile avatar){
        User user = userService.getById(userId);
        if (!user.getAvatar().equals(userProperties.getAvatarName()))
            imageService.removeImage(user.getAvatar());
        String saved = imageService.upload(imageMapper.fromDTO(new ImageDTO(avatar)));
        userService.uploadAvatar(userId, saved); 
        return ResponseEntity.ok()
                .body(saved);
    }

}
