package com.example.walkers.exception;

public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

    public EmailAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
