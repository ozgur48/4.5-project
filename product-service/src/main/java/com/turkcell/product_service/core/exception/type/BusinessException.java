package com.turkcell.product_service.core.exception.type;

public class BusinessException extends RuntimeException
{
    public BusinessException(String message) {
        super(message);
    }
}