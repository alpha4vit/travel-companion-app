package by.gurinovich.travelcompanionsearch.util.validator;

import by.gurinovich.travelcompanionsearch.dto.PostDTO;
import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;
import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.util.mapper.DateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return PostDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostDTO postDTO = (PostDTO) target;
        Map<String, String> map = new HashMap<>();
        if (Instant.parse(postDTO.getDateThere()).isAfter(Instant.parse(postDTO.getDateBack())))
            map.put("date", "Дата отправления не может быть позже даты прибытия!");
        if (Instant.parse(postDTO.getDateThere()).isBefore(Instant.now()))
            map.put("date", "Дата отправления не может в прошлом!");
        if (Instant.parse(postDTO.getDateBack()).isBefore(Instant.now()))
            if (map.containsKey("date"))
                map.put("date", "Даты были введены некорректно!");
            else
                map.put("date", "Дата прибытия не может в прошлом!");
        if (errors.hasFieldErrors()){
            errors.getFieldErrors().forEach((error) -> {
                map.put(error.getField(), error.getDefaultMessage());
            });
        }
        if (map.size() != 0)
            throw new InvalidRequestException(map);
    }
}
