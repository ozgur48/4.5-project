package com.turkcell.product_service.interfaces.web;

import java.util.List;

import com.turkcell.product_service.application.command.DeleteProductByIdCommand;
import com.turkcell.product_service.application.dto.DeletedProductResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.turkcell.product_service.application.command.CreateProductCommand;
import com.turkcell.product_service.application.command.UpdateProductCommand;
import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdatedProductResponse;
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
    private final CommandHandler<UpdateProductCommand, UpdatedProductResponse> updateProductQueryHandler;
    private final CommandHandler<DeleteProductByIdCommand, DeletedProductResponse> deleteProductQueryHandler;

    public ProductsController(QueryHandler<ListProductsQuery, List<ProductResponse>> listProductQueryHandler, QueryHandler<FindProductByIdQuery, ProductResponse> findtProductByIdQueryHandler, CommandHandler<CreateProductCommand, CreatedProductResponse> createProductQueryHandler, com.turkcell.product_service.core.cqrs.CommandHandler<com.turkcell.product_service.application.command.UpdateProductCommand,com.turkcell.product_service.application.dto.UpdatedProductResponse> updateProductQueryHandler, CommandHandler<DeleteProductByIdCommand, DeletedProductResponse> deleteProductQueryHandler) {
        this.listProductQueryHandler = listProductQueryHandler;
        this.findtProductByIdQueryHandler = findtProductByIdQueryHandler;
        this.createProductQueryHandler = createProductQueryHandler;
        this.updateProductQueryHandler = updateProductQueryHandler;
        this.deleteProductQueryHandler = deleteProductQueryHandler;
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
    @PutMapping("/{id}")
    public UpdatedProductResponse updateProduct(@Valid @PathVariable String id, 
    @RequestBody UpdateProductCommand command) {
        // urlden gelen id'yi command'e ekliyoruz
        UpdateProductCommand finalCommand = new UpdateProductCommand(
            id,
            command.name(),
            command.description(),
            command.price(),
            command.currency(),
            command.stock()
        );
        return updateProductQueryHandler.handle(finalCommand);
    } 
    @DeleteMapping("/{id}")
    public DeletedProductResponse delete(@Valid @PathVariable String id, DeleteProductByIdCommand deleteProductByIdCommand){
        DeleteProductByIdCommand finalCommand = new DeleteProductByIdCommand(
                id
        );
        return deleteProductQueryHandler.handle(finalCommand);
    }
}
