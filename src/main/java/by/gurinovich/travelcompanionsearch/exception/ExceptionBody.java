package by.gurinovich.travelcompanionsearch.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionBody {
    private String message;
    private Map<String, String> errors;
}