package com.example.barseek_auth_mservice.exception;


import com.example.barseek_auth_mservice.exception.customExceptions.AuthException;
import com.example.barseek_auth_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_auth_mservice.exception.customExceptions.InvalidPasswordException;
import com.example.barseek_auth_mservice.exception.customExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthServiceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidData(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuth(AuthException ex) {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong pass!!!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobal(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!....");
    }

}
