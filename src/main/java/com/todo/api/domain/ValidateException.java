package com.todo.api.domain;

public class ValidateException extends RuntimeException {
    public ValidateException(String message) {
        super(message);
    }
}
