package com.turkcell.payment_service.Domain.Model;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {
    public Money{
        Objects.requireNonNull(amount,"Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        if(amount.signum() < 0 ){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(currency.isBlank()){
            throw new IllegalArgumentException("Currency cannot be blank");
        }
    }
}
