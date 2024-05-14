package vla.kyr.restfullapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vla.kyr.restfullapi.exception.AgeException;
import vla.kyr.restfullapi.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getObjectName(); // Use getObjectName() instead of getField()
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> handleSearchException(ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage: ", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AgeException.class)
    public Map<String,String> handleAgeException(AgeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage: ", ex.getMessage());
        return errors;
    }


}

