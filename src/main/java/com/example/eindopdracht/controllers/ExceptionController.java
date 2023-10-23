package com.example.eindopdracht.controllers;

import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Annotation which allows to handle exceptions across the whole application, not just to an individual controller. It's used to define global exception handling rules.
public class ExceptionController {

    //    When an exception of RecordNotFoundException is thrown in the application, this method catches it and returns a ResponseEntity with the exception message and an HTTP status of NOT_FOUND (404).
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    When an exception of IdNotFoundException is thrown in the application, this method catches it and returns a ResponseEntity with the exception message and an HTTP status of NOT_FOUND (404).
    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<Object> exceptionId(IdNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
