package com.turkcell.payment_service.Domain.Model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record PaymentId(UUID value) implements Serializable {
    public PaymentId{
        Objects.requireNonNull(value, "PaymentId cannot be null");
    }
    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID());
    }
}
