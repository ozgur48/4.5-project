package com.turkcell.product_service.application.command;

import java.math.BigDecimal;

import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import com.turkcell.product_service.core.cqrs.Command;

public record UpdateProductCommand(
    String id,
    String name,
    String description,
    BigDecimal price,
    String currency,
    Integer stock
) implements Command<UpdatedProductResponse>{}
