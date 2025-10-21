package com.turkcell.product_service.domain;

import java.util.List;
import java.util.Optional;

import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.model.ProductId;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(ProductId productid);
    List<Product> findAllPaged(Integer pageIndex, Integer pageSize);
    List<Product> findAll();

    void delete(ProductId productId);
}
