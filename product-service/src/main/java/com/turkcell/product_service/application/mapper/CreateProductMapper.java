package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.command.CreateProductCommand;
import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class CreateProductMapper {
    public Product toDomain(CreateProductCommand command){
        return Product.create(
                ProductName.of((command.name())),
                ProductDescription.of(command.description()),
                Price.of(command.price()),
                Currency.of(command.currency()),
                Stock.of(command.stock())
        );
    }

    public CreatedProductResponse toResponse(Product product){
        return new CreatedProductResponse(
                product.getId().getValue(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getPrice().getValue(),
                product.getCurrency().getCode(),
                product.getStock().getValue()
        );
    }
}
