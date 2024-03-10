package com.example.storeservice.exception;

public class ProductDuplicateCreateException extends RuntimeException {
    public static final String ORDER_DUPLICATE_CREATE = "Order already created";

    public ProductDuplicateCreateException() {
        super(ORDER_DUPLICATE_CREATE);
    }
    public ProductDuplicateCreateException(String message) {
        super(message);
    }
}
