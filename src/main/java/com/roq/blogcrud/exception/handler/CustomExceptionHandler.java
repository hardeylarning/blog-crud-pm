package com.roq.blogcrud.exception.handler;

import com.roq.blogcrud.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.error(fieldName + ": " + errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> badRequest(BadRequestException badRequestException) {
        ErrorMessage message = new ErrorMessage(String.valueOf(HttpStatus.BAD_REQUEST), badRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFound(NotFoundException notFoundException) {
        ErrorMessage message = new ErrorMessage(String.valueOf(HttpStatus.NOT_FOUND), notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> forbidden(ForbiddenException forbiddenException) {
        ErrorMessage message = new ErrorMessage(String.valueOf(HttpStatus.FORBIDDEN), forbiddenException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> unauthorized(UnauthorizedException unauthorizedException) {
        ErrorMessage message = new ErrorMessage(String.valueOf(HttpStatus.UNAUTHORIZED), unauthorizedException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }


}
