package com.turkcell.product_service.infrastructure;

import com.turkcell.product_service.domain.model.*;

import java.util.UUID;

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
