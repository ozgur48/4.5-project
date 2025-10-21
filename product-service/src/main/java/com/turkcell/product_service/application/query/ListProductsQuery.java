package com.turkcell.product_service.application.query;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.core.cqrs.Query;
import jakarta.validation.constraints.Min;
import java.util.List;

public record ListProductsQuery(
        @Min(0) Integer pageIndex,
        @Min(1)  Integer pageSize
) implements Query<List<ProductResponse>> { }
