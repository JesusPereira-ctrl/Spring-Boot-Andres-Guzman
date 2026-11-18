package com.andres.springboot.di.app.springbootdi.services;

import com.andres.springboot.di.app.springbootdi.models.Product;
import com.andres.springboot.di.app.springbootdi.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(p -> {
                    Double priceImp = p.getPrice() * 1.25d;
                    p.setPrice(priceImp.longValue());
                    return p;
                }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
