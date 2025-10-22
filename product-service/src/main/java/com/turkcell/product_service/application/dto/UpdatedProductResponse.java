package com.turkcell.product_service.application.dto;

import java.math.BigDecimal;

public record UpdatedProductResponse(
    String name,
    String description,
    BigDecimal price,
    String currency,
    Integer stock
) {}
