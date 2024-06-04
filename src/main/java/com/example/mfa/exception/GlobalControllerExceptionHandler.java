package com.example.mfa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling exceptions across all controllers.
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * This method is invoked when a MethodArgumentNotValidException is thrown, typically
     * as a result of validation failures on request parameters. It extracts the validation errors
     * and returns them in a map, with the field names as keys and the error messages as values.
     * @param ex the exception thrown when method arguments are not valid
     * @return a response entity containing a map of field names and error messages, with HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var err = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
