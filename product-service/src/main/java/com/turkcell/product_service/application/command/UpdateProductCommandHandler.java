package com.turkcell.product_service.application.command;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import com.turkcell.product_service.application.mapper.UpdateProductMapper;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.core.exception.type.BusinessException;
import com.turkcell.product_service.domain.ProductRepository;
import com.turkcell.product_service.domain.model.Currency;
import com.turkcell.product_service.domain.model.Price;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductDescription;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.model.ProductName;
import com.turkcell.product_service.domain.model.Stock;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand, UpdatedProductResponse> {


    private final ProductRepository productRepository;
    private final UpdateProductMapper updateProductMapper;

    public UpdateProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.updateProductMapper = new UpdateProductMapper();
    }
    

    @Override
    public UpdatedProductResponse handle(UpdateProductCommand command) {
        // string gelen id'yi vo id'ye dönüştürdük
        ProductId productId = ProductId.of(command.id());
        Product existingProduct = productRepository.findById(productId).orElseThrow(()-> new BusinessException("Bu id'li product bulunamadı"));
        
        // burada istekden gelen değerleri vo degerlerine dönüştürdük.
        existingProduct.update(
            ProductName.of(command.name()),
            ProductDescription.of(command.description()),
            Price.of(command.price()),
            Currency.of(command.currency()),
            Stock.of(command.stock())
        );
        Product saveProduct = productRepository.save(existingProduct);
        return updateProductMapper.toResponse(saveProduct);
    }

}
