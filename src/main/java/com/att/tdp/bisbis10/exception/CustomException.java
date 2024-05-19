package com.att.tdp.bisbis10.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class to handle application-specific exceptions.
 */

public class CustomException extends RuntimeException {
    private final HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
