package com.jeremias.oauthtest.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyTakenException(String msg) {
        super(msg);
    }
}
