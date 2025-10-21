package com.turkcell.product_service.application.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer stock
) {
}
