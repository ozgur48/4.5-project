package com.turkcell.product_service.interfaces.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.product_service.application.command.CreateProductCommand;
import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.query.FindProductByIdQuery;
import com.turkcell.product_service.application.query.ListProductsQuery;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.core.cqrs.QueryHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductsController {
    private final QueryHandler<ListProductsQuery, List<ProductResponse>> listProductQueryHandler;
    private final QueryHandler<FindProductByIdQuery, ProductResponse> findtProductByIdQueryHandler;
    private final CommandHandler<CreateProductCommand, CreatedProductResponse> createProductQueryHandler;

    public ProductsController(QueryHandler<ListProductsQuery, List<ProductResponse>> listProductQueryHandler, QueryHandler<FindProductByIdQuery, ProductResponse> findtProductByIdQueryHandler, CommandHandler<CreateProductCommand, CreatedProductResponse> createProductQueryHandler) {
        this.listProductQueryHandler = listProductQueryHandler;
        this.findtProductByIdQueryHandler = findtProductByIdQueryHandler;
        this.createProductQueryHandler = createProductQueryHandler;
    }


    @GetMapping
    public List<ProductResponse> getProducts(ListProductsQuery query){
        return listProductQueryHandler.handle(query);
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(FindProductByIdQuery query){
        return findtProductByIdQueryHandler.handle(query);
    }
    @PostMapping
    public CreatedProductResponse create(@RequestBody @Valid CreateProductCommand command){
        return createProductQueryHandler.handle(command);
    }
}
