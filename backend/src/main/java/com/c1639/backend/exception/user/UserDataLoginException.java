package com.c1639.backend.exception.user;

public class UserDataLoginException extends RuntimeException {

    public UserDataLoginException() {
        super();
    }

    public UserDataLoginException(String message) {
        super(message);
    }

    public UserDataLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDataLoginException(Throwable cause) {
        super(cause);
    }

    protected UserDataLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
