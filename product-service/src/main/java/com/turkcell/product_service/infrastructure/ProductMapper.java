package com.turkcell.product_service.infrastructure;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.domain.model.Currency;
import com.turkcell.product_service.domain.model.Price;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductDescription;
import com.turkcell.product_service.domain.model.ProductId;
import com.turkcell.product_service.domain.model.ProductName;
import com.turkcell.product_service.domain.model.Stock;

@Component
public class ProductMapper {
    public ProductEntity ToEntity(Product product){
        ProductEntity entity = new ProductEntity();
        UUID uuid = UUID.fromString(product.getId().toString());

        entity.setId(uuid);
        entity.setName(product.getName().getValue());
        entity.setDescription(product.getDescription().getValue());
        entity.setPrice(product.getPrice().getValue());
        entity.setCurrency(product.getCurrency().getCode());
        entity.setStock(product.getStock().getValue());
        entity.setCreatedAt(product.getCreatedAt());
        entity.setUpdatedAt(product.getUpdatedAt());
        return entity;
    }
    public Product toDomain(ProductEntity entity){
        return Product.reconstruct(
                ProductId.of(entity.getId().toString()),
                ProductName.of(entity.getName()),
                ProductDescription.of(entity.getDescription()),
                // bu şekilde dbden aldıgımız ham veriyi doğru bir VO'e dönüştürüyoruz.
                Price.of(entity.getPrice()),
                Currency.of(entity.getCurrency()),
                Stock.of(entity.getStock()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
