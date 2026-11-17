package com.example.demo.exception;

public class ValidationFailException extends Exception {
    private String message;

    public ValidationFailException(String message) {
        super(message);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
