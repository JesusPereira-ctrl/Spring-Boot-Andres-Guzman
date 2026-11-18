package com.andres.springboot.di.app.springbootdi.services;

import com.andres.springboot.di.app.springbootdi.models.Product;
import com.andres.springboot.di.app.springbootdi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final Double tax;

    public ProductServiceImpl(
            @Qualifier("productJson") ProductRepository productRepository,
            @Value("${config.price.tax}") Double tax
    ) {
        this.productRepository = productRepository;
        this.tax = tax;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(p -> {
            double priceTax = p.getPrice() * tax;
            Product newProd = (Product) p.clone();
            newProd.setPrice((long) priceTax);
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
