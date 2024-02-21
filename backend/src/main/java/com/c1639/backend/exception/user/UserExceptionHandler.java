package com.c1639.backend.exception.user;

import com.c1639.backend.exception.ApplicationExceptionResponse;
import com.c1639.backend.exception.ExceptionUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(1)
public class UserExceptionHandler {

    // Email user already exists when trying to register
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApplicationExceptionResponse> userAlreadyExistsException(UserAlreadyExistsException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>(Map.of(ex.getClass().getSimpleName(), ex.getMessage()));
        ApplicationExceptionResponse errorResponse = ExceptionUtils.createResponse(HttpStatus.BAD_REQUEST, req, errors);

        return ResponseEntity.status(400).body(errorResponse);
    }

    // User not found when trying to login
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApplicationExceptionResponse> userNotFoundException(UserNotFoundException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>(Map.of(ex.getClass().getSimpleName(), ex.getMessage()));
        ApplicationExceptionResponse errorResponse = ExceptionUtils.createResponse(HttpStatus.NOT_FOUND, req, errors);

        return ResponseEntity.status(404).body(errorResponse);

    }

    // User password is incorrect when trying to login
    @ExceptionHandler(UserDataLoginException.class)
    public ResponseEntity<ApplicationExceptionResponse> userDataLoginException(UserDataLoginException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>(Map.of(ex.getClass().getSimpleName(), ex.getMessage()));
        ApplicationExceptionResponse errorResponse = ExceptionUtils.createResponse(HttpStatus.BAD_REQUEST, req, errors);

        return ResponseEntity.status(400).body(errorResponse);
    }

}
