package com.turkcell.product_service.application.command;

import com.turkcell.product_service.application.dto.DeletedProductResponse;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.core.exception.detail.ProductNotFoundException;
import com.turkcell.product_service.core.exception.type.BusinessException;
import com.turkcell.product_service.domain.ProductRepository;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductByIdCommandHandler implements CommandHandler<DeleteProductByIdCommand, DeletedProductResponse> {
    private final ProductRepository productRepository;

    public DeleteProductByIdCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public DeletedProductResponse handle(DeleteProductByIdCommand command) {
        ProductId id = ProductId.of(command.id());
        Product existsProduct = productRepository
                .findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Id'li product bulunamadı", new RuntimeException()));
        productRepository.delete(id);
        return new DeletedProductResponse(command.id());
    }
}
