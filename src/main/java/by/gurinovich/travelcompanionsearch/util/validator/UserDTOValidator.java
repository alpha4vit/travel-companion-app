package by.gurinovich.travelcompanionsearch.util.validator;

import by.gurinovich.travelcompanionsearch.dto.UserDTO;
import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class UserDTOValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        HashMap<String, String> map = new HashMap<>();
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent())
            map.put("username", "Имя уже используется!");
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
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
