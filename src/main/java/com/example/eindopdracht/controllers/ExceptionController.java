package com.example.eindopdracht.controllers;

import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.exceptions.IncorrectEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Global exception handler to handle exceptions across multiple controllers
@ControllerAdvice
public class ExceptionController {

    // Handling IdNotFoundException
    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<Object> exceptionId(IdNotFoundException exception) {
        // Returning the exception message in the response body with HTTP status code 404 (Not Found)
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handling IncorrectEmailException
    @ExceptionHandler(value = IncorrectEmailException.class)
    public ResponseEntity<Object> exceptionId(IncorrectEmailException exception) {
        // Returning the exception message in the response body with HTTP status code 422 (Unprocessable Entity)
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
