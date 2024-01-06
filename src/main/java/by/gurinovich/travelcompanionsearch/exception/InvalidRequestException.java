package by.gurinovich.travelcompanionsearch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvalidRequestException extends RuntimeException{
    private Map<String, String> errors;

    public InvalidRequestException(String message) {
        super(message);
    }
}
