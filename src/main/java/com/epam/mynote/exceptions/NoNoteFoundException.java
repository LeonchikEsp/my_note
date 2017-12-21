package com.epam.mynote.exceptions;

public class NoNoteFoundException extends RuntimeException {

    public NoNoteFoundException() {
    }

    public NoNoteFoundException(String message) {
        super(message);
    }
}
