package com.turkcell.product_service.application.query;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductResponseMapper;
import com.turkcell.product_service.core.cqrs.QueryHandler;
import com.turkcell.product_service.domain.ProductRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ListProductsQueryHandler implements QueryHandler<ListProductsQuery, List<ProductResponse>> {
    private final ProductResponseMapper productResponseMapper;
    private final ProductRepository productRepository;

    public ListProductsQueryHandler(ProductResponseMapper productResponseMapper, ProductRepository productRepository) {
        this.productResponseMapper = productResponseMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> handle(ListProductsQuery query) {
        return productRepository
                .findAllPaged(query.pageIndex(), query.pageSize())
                .stream()
                .map(productResponseMapper::toResponse)
                .toList();
    }
}
