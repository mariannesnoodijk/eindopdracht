package com.example.eindopdracht.exceptions;

public class IncorrectEmailException extends RuntimeException{
    public IncorrectEmailException(String message) { // exception filled with message
        super(message);
    }
}
