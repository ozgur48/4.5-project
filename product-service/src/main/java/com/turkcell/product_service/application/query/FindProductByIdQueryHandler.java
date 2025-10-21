package com.turkcell.product_service.application.query;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductResponseMapper;
import com.turkcell.product_service.core.cqrs.QueryHandler;
import com.turkcell.product_service.core.exception.type.BusinessException;
import com.turkcell.product_service.domain.ProductRepository;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;
import org.springframework.stereotype.Component;

@Component
public class FindProductByIdQueryHandler implements QueryHandler<FindProductByIdQuery, ProductResponse> {
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;

    public FindProductByIdQueryHandler(ProductRepository productRepository, ProductResponseMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public ProductResponse handle(FindProductByIdQuery query) {
        ProductId productId = ProductId.of(query.id());
        return productRepository.findById(productId).map(productResponseMapper::toResponse).orElseThrow(()-> new BusinessException("Id'li product bulunamadı"));
    }
}
