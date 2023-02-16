package com.arithmeticservices.apigateway.exception;

public class JwtTokenNotValidException extends RuntimeException {

    private final String message;

    public JwtTokenNotValidException(String message) {
        super("Token not valid!");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
