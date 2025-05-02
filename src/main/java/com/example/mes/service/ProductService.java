package com.example.mes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mes.entity.Product;
import com.example.mes.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}