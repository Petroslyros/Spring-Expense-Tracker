package com.spring.expenses.expensestracker.core;

import com.spring.expenses.expensestracker.core.exceptions.*;
import com.spring.expenses.expensestracker.dto.ResponseMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice  // Tells Spring this class will globally handle exceptions thrown by controllers
public class ErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles validation errors thrown as ValidationException.
     * Collects field-level validation errors into a map for the client to consume.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(ValidationException e) {
        log.error("Validation failed. Message={}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // Returns all validation errors with HTTP 400 (Bad Request)
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles cases where an entity was not found.
     * Returns an error code/message pair with HTTP 409 (Conflict).
     */
    @ExceptionHandler(AppObjectNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppObjectNotFoundException e) {
        log.warn("Entity not found. Message={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseMessageDTO(e.getCode(), e.getMessage()));
    }

    /**
     * Handles invalid input arguments for application operations.
     * Returns an error code/message pair with HTTP 400 (Bad Request).
     */
    @ExceptionHandler(AppObjectInvalidArgumentException.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppObjectInvalidArgumentException e) {
        log.warn("Invalid Argument. Message={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessageDTO(e.getCode(), e.getMessage()));
    }

    /**
     * Handles cases where an entity already exists.
     * Returns an error code/message pair with HTTP 409 (Conflict).
     */
    @ExceptionHandler(AppObjectAlreadyExists.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppObjectAlreadyExists e) {
        log.warn("Entity already exists. Message={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseMessageDTO(e.getCode(), e.getMessage()));
    }

    /**
     * Handles authorization errors.
     * Returns an error code/message pair with HTTP 403 (Forbidden).
     */
    @ExceptionHandler(AppObjectNotAuthorizedException.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppObjectNotAuthorizedException e, WebRequest request) {
        log.warn("Authorization failed for URI={}. Message={}", request.getDescription(false), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseMessageDTO(e.getCode(), e.getMessage()));
    }

    /**
     * Handles server-side exceptions specific to your application.
     * Returns an error code/message pair with HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(AppServerException.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppServerException e) {
        log.warn("Server error with message={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessageDTO(e.getCode(), e.getMessage()));
    }

    /**
     * Handles IO errors such as file upload failures.
     * Returns a custom FILE_UPLOAD_FAILED code with HTTP 500.
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(IOException e) {
        log.error("File upload failed with message={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessageDTO("FILE_UPLOAD_FAILED", e.getMessage()));
    }

    /**
     * Handles failed login attempts due to bad credentials.
     * Returns UNAUTHORIZED (HTTP 401).
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseMessageDTO> handleBadCredentials(BadCredentialsException e, HttpServletRequest request) {
        log.warn("Failed login attempt for IP={}", request.getRemoteAddr());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseMessageDTO("UNAUTHORIZED", e.getMessage()));
    }

    /**
     * Handles more general authentication errors (e.g. account locked, disabled, expired).
     * Returns a specific error code depending on the exception type with HTTP 403.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessageDTO> handleBadCredentials(AuthenticationException e, HttpServletRequest request) {
        log.warn("Failed login for IP={}", request.getRemoteAddr());

        // Determine specific error code based on the exception subclass
        String errorCode = switch (e.getClass().getSimpleName()) {
            case "DisabledException" -> "ACCOUNT_DISABLED";
            case "LockedException" -> "ACCOUNT_LOCKED";
            case "AccountExpiredException" -> "ACCOUNT_EXPIRED";
            case "CredentialsExpiredException" -> "CREDENTIALS_EXPIRED";
            default -> "ACCOUNT_ERROR";
        };

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseMessageDTO(errorCode, e.getMessage()));
    }
}
