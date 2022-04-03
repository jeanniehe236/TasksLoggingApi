package com.demo.errorhandling;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    private HttpStatus status;

    ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}