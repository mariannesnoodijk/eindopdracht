package com.example.eindopdracht.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) { // exception filled with message
        super(message);
    }

    public RecordNotFoundException() {     // default exception
        super();
    }

    }
