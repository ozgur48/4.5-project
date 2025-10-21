package com.turkcell.product_service.application.query;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.core.cqrs.Query;


public record FindProductByIdQuery(
        String id
) implements Query<ProductResponse> {
}
