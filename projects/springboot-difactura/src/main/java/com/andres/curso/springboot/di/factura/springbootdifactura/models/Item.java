package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    private Product product;

    private Integer quantity;

    public Item() {
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @JsonProperty
    public Product getProduct() {
        return product;
    }

    @JsonProperty
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty
    public int getImporte() {
        return quantity * product.getPrice();
    }
}
