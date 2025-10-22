package com.turkcell.product_service.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Product Entity - Core domain entity representing a product in the e-commerce system
 * Following DDD principles with rich domain model
 */
public class Product {
    private final ProductId id;
    private ProductName name;
    private ProductDescription description;
    private Price price;
    private Currency currency;
    private Stock stock;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Private constructor to enforce creation through factory methods
    private Product(ProductId id, ProductName name, ProductDescription description, 
                   Price price, Currency currency, Stock stock) {
        this.id = Objects.requireNonNull(id, "Product ID cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.description = Objects.requireNonNull(description, "Product description cannot be null");
        this.price = Objects.requireNonNull(price, "Product price cannot be null");
        this.currency = Objects.requireNonNull(currency, "Product currency cannot be null");
        this.stock = Objects.requireNonNull(stock, "Product stock cannot be null");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Factory method for creating new products
    public static Product create(ProductName name, ProductDescription description, 
                                Price price, Currency currency, Stock stock) {
        return new Product(ProductId.generate(), name, description, price, currency, stock);
    }

    // Factory method for reconstructing existing products (from persistence)
    public static Product reconstruct(ProductId id, ProductName name, ProductDescription description,
                                     Price price, Currency currency, Stock stock,
                                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        Product product = new Product(id, name, description, price, currency, stock);
        product.updatedAt = updatedAt;
        return product;
    }
    public Product update(ProductName name, ProductDescription description, 
    Price price, Currency currency, Stock stock ){
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        return this;
    }

    // Business methods
    public void updateName(ProductName newName) {
        this.name = Objects.requireNonNull(newName, "Product name cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(ProductDescription newDescription) {
        this.description = Objects.requireNonNull(newDescription, "Product description cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePrice(Price newPrice, Currency newCurrency) {
        this.price = Objects.requireNonNull(newPrice, "Product price cannot be null");
        this.currency = Objects.requireNonNull(newCurrency, "Product currency cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public void addStock(Stock quantity) {
        this.stock = this.stock.add(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    public void addStock(int quantity) {
        this.stock = this.stock.add(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    public void reduceStock(Stock quantity) {
        if (!this.stock.hasEnough(quantity)) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + this.stock.getValue() + 
                                             ", Required: " + quantity.getValue());
        }
        this.stock = this.stock.subtract(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    public void reduceStock(int quantity) {
        if (!this.stock.hasEnough(quantity)) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + this.stock.getValue() + 
                                             ", Required: " + quantity);
        }
        this.stock = this.stock.subtract(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    public void setStock(Stock newStock) {
        this.stock = Objects.requireNonNull(newStock, "Product stock cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    // Query methods
    public boolean isAvailable() {
        return this.stock.isAvailable();
    }

    public boolean hasStock() {
        return this.stock.isPositive();
    }

    public boolean hasEnoughStock(int requiredQuantity) {
        return this.stock.hasEnough(requiredQuantity);
    }

    public boolean hasEnoughStock(Stock requiredQuantity) {
        return this.stock.hasEnough(requiredQuantity);
    }

    // Getters
    public ProductId getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public ProductDescription getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", currency=" + currency +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
