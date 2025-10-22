package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import com.turkcell.product_service.domain.model.Product;

public class UpdateProductMapper {
    public UpdatedProductResponse toResponse(Product product){
        return new UpdatedProductResponse(
            product.getName().getValue(),
            product.getDescription().getValue(),
            product.getPrice().getValue(),
            product.getCurrency().getCode(),
            product.getStock().getValue()
        );
    }
}
