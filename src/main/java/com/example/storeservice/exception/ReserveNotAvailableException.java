package com.example.storeservice.exception;

public class ReserveNotAvailableException extends RuntimeException {
    public ReserveNotAvailableException(String message) {
        super(message);
    }
}
