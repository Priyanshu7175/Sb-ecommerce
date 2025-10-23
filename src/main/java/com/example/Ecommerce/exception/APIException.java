package com.example.Ecommerce.exception;

public class APIException extends RuntimeException {

    private static final long exceptionID = 1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
