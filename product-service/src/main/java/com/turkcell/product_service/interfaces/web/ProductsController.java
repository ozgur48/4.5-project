package com.turkcell.product_service.interfaces.web;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.query.FindProductByIdQuery;
import com.turkcell.product_service.application.query.ListProductsQuery;
import com.turkcell.product_service.core.cqrs.QueryHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final QueryHandler<ListProductsQuery, List<ProductResponse>> listProductQueryHandler;
    private final QueryHandler<FindProductByIdQuery, ProductResponse> findtProductByIdQueryHandler;

    public ProductsController(QueryHandler<ListProductsQuery, List<ProductResponse>> listProductQueryHandler, QueryHandler<FindProductByIdQuery, ProductResponse> findtProductByIdQueryHandler) {
        this.listProductQueryHandler = listProductQueryHandler;
        this.findtProductByIdQueryHandler = findtProductByIdQueryHandler;
    }


    @GetMapping
    public List<ProductResponse> getProducts(ListProductsQuery query){
        return listProductQueryHandler.handle(query);
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(FindProductByIdQuery query){
        return findtProductByIdQueryHandler.handle(query);
    }
}
