package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper {
    public ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getPrice().getValue(),
                product.getCurrency().getCode(),
                product.getStock().getValue()
        );
    }
}
