package com.example.barseek_bar_mservice.exception;


import com.example.barseek_bar_mservice.exception.customExceptions.BarNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.DrinkNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_bar_mservice.exception.customExceptions.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class BarServiceExceptionHandler {

    @ExceptionHandler(BarNotFoundException.class)
    public ResponseEntity<String> handleBarNotFound(BarNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DrinkNotFoundException.class)
    public ResponseEntity<String> handleDrinkNotFound(DrinkNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidData(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternal(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something gone wrong!");
    }
}
