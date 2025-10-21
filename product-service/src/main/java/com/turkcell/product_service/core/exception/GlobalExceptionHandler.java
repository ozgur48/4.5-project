package com.turkcell.product_service.core.exception;

import com.turkcell.product_service.core.exception.detail.ExceptionDetails;
import com.turkcell.product_service.core.exception.detail.ValidationExceptionDetails;
import com.turkcell.product_service.core.exception.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDetails handleValidationException(MethodArgumentNotValidException ex)
    {
        return new ValidationExceptionDetails(
                "Validasyon hatası",
                ex.getBindingResult().getAllErrors());
    }

    // Her ex. handler fırlayan ex'ı parametre olarak alır.
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleBusinessException(BusinessException e)
    {
        return new ExceptionDetails(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException()
    {
        return "Runtime Error";
    }
}
