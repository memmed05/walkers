package com.example.walkers.exception;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }

    public UsernameAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
