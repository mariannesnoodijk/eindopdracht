package com.example.eindopdracht.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) { // exception filled with message
        super(message);
    }

    public IdNotFoundException() { // default exception
        super();
    }
}