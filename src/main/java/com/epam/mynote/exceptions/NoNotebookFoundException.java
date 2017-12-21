package com.epam.mynote.exceptions;

public class NoNotebookFoundException extends RuntimeException {

    public NoNotebookFoundException() {
    }

    public NoNotebookFoundException(String message) {
        super(message);
    }
}
