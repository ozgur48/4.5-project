package com.turkcell.product_service.application.command;

import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotBlank @Size(min=2, max=100) String name,
        @NotBlank @Size(min=3,max = 500) String description,
        @Positive BigDecimal price,
        @NotBlank @Size(min = 3, max = 3,
                message = "Para birimi kodu tam olarak 3 karakter uzunluğunda olmalıdır (Örn: TRY).")
        String currency,
        @Positive Integer stock
) implements Command<CreatedProductResponse> { }
