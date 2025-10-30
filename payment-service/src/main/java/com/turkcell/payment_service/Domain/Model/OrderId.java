package com.turkcell.payment_service.Domain.Model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record OrderId(UUID value) implements Serializable {
    public OrderId{
        Objects.requireNonNull(value, "OrderId cannot be null");
    }
}
