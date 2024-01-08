package by.gurinovich.travelcompanionsearch.util.validator;

import by.gurinovich.travelcompanionsearch.dto.PostResponseDTO;
import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostResponseDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostResponseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Map<String, String> map  = new HashMap<>();
        if (errors.hasFieldErrors()){
            errors.getFieldErrors().forEach(error -> {
                map.put(error.getField(), error.getDefaultMessage());
            });
        }
        if (map.size()!=0)
            throw new InvalidRequestException(map);
    }
}
