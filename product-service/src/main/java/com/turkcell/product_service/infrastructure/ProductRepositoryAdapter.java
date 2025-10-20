package com.turkcell.product_service.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.turkcell.product_service.domain.ProductRepository;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;

public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }


    @Override
    public Product save(Product product) {
        // domain -> entity
        ProductEntity entity = productMapper.ToEntity(product);
        // db'ye jpa ile kaydeder
        entity = jpaProductRepository.save(entity);
        // entity -> domain
        return productMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId productid) {
        UUID uuid = UUID.fromString(productid.toString());
        return jpaProductRepository.findById(uuid).map(productMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream().map(productMapper::toDomain).toList();
    }

    @Override
    public void delete(ProductId productId) {
        UUID uuid = UUID.fromString(productId.getValue());
        jpaProductRepository.deleteById(uuid);
    }

}
