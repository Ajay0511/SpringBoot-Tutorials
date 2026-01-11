package com.example.employeeservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    Why Not Handle Exceptions in Controller?
        - Repetition
        - Messy code
        - No consistency

    Interview Rule of Thumb:
        Controllers should be thin, exception handling should be centralized.

    @RestControllerAdvice in Spring is a powerful annotation for centralizing exception
    handling across multiple controllers, allowing you to define global or targeted exception handlers in a single component.
    It intercepts exceptions thrown by controllers (like those annotated with @RequestMapping) 
    and delegates them to specific methods within the @RestControllerAdvice class, 
    typically using @ExceptionHandler, to provide consistent error responses (like JSON/XML with status codes)
    without cluttering individual controller methods with try-catch blocks.
    
    @RestControllerAdvice combines @ControllerAdvice with @ResponseBody, so exception handler methods
    automatically serialize responses to JSON by default.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(
        MethodArgumentNotValidException ex
    ){
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errorsMap.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }
}
