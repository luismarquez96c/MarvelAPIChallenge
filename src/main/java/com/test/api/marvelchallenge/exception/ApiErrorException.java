package com.test.api.marvelchallenge.exception;

public class ApiErrorException extends RuntimeException {

    public ApiErrorException() {
        super();
    }

    public ApiErrorException(String message) {
        super(message);
    }

    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorException(Throwable cause) {
        super(cause);
    }
}
