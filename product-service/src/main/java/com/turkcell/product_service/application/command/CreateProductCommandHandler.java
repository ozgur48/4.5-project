package com.turkcell.product_service.application.command;

import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.application.mapper.CreateProductMapper;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.domain.ProductRepository;
import com.turkcell.product_service.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand, CreatedProductResponse> {
    private final ProductRepository productRepository;
    private final CreateProductMapper createProductMapper;

    public CreateProductCommandHandler(ProductRepository productRepository, CreateProductMapper createProductMapper) {
        this.productRepository = productRepository;
        this.createProductMapper = createProductMapper;
    }


    @Override
    public CreatedProductResponse handle(CreateProductCommand command) {
        Product product = createProductMapper.toDomain(command);
        product = productRepository.save(product);
        return createProductMapper.toResponse(product);
    }
}
