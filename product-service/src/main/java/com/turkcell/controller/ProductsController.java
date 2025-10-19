package com.turkcell.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    
    @GetMapping
    public String getProducts() {
        return "Products";
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Product created";
    }
    @PutMapping("/{id}")
    public String updateProduct() {
        return "Product updated";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct() {
        return "Product deleted";
    }



}
