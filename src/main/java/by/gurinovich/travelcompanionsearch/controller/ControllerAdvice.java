package by.gurinovich.travelcompanionsearch.controller;

import by.gurinovich.travelcompanionsearch.exception.ExceptionBody;
import by.gurinovich.travelcompanionsearch.exception.InvalidRequestException;
import by.gurinovich.travelcompanionsearch.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(final ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        ExceptionBody exceptionBody = ExceptionBody.builder()
                .message(ex.getMessage())
                .errors(errors)
                .build();
        return exceptionBody;
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleResourceNotFound(final InvalidRequestException ex) {
        Map<String, String> errors = new HashMap<>();
        ExceptionBody exceptionBody = ExceptionBody.builder()
                .message(ex.getMessage())
                .errors(errors)
                .build();
        return exceptionBody;
    }

}
