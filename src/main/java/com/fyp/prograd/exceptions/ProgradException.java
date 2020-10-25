package com.fyp.prograd.exceptions;

public class ProgradException extends RuntimeException {
    public ProgradException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ProgradException(String exMessage) {
        super(exMessage);
    }
}
