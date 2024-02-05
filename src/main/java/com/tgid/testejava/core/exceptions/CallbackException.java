package com.tgid.testejava.core.exceptions;

public class CallbackException extends RuntimeException{
    
    public CallbackException(String message) {
        super(message);
    }

    public CallbackException(String message, Throwable cause) {
        super(message, cause);
    }
}
