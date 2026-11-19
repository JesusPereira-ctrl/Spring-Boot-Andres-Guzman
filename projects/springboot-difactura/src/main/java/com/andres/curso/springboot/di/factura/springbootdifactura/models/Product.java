package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private final String name;

    private final Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Integer getPrice() {
        return price;
    }
}
