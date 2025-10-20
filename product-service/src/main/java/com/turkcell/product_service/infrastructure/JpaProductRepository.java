package com.turkcell.product_service.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
}
