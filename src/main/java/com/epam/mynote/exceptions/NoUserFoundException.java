package com.epam.mynote.exceptions;

public class NoUserFoundException extends RuntimeException {

    public NoUserFoundException() {
    }

    public NoUserFoundException(String message) {
        super(message);
    }
}
