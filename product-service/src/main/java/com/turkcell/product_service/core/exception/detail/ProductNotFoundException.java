package com.turkcell.product_service.core.exception.detail;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
