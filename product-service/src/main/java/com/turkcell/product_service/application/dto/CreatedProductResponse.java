package com.turkcell.product_service.application.dto;

import java.math.BigDecimal;

public record CreatedProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer stock
) {
}
