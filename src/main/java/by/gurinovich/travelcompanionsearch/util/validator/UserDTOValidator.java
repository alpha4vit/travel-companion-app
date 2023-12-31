package by.gurinovich.travelcompanionsearch.util.validator;

import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.UserRepository;
import by.gurinovich.travelcompanionsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class UserDTOValidator implements Validator {

    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        User existed = userService.getById(userDTO.getId());
        HashMap<String, String> map = new HashMap<>();
        if (!userDTO.getUsername().equals(existed.getUsername()) && userRepository.findByUsername(userDTO.getUsername()).isPresent())
            map.put("username", "Имя уже используется!");
        if (!userDTO.getEmail().equals(existed.getEmail()) && userRepository.findByEmail(userDTO.getEmail()).isPresent())
            map.put("email", "Электронная почта уже используется!");
        if (errors.getFieldErrors().size() != 0){
            errors.getFieldErrors().forEach((error) -> {
                map.put(error.getField(), error.getDefaultMessage());
            });
        }
        if(map.size() != 0)
            throw new InvalidRequestException(map);
    }
}
