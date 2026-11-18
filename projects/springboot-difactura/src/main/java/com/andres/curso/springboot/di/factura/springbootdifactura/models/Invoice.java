package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Invoice {

    private final Client client;

    @Value("${invoice.description}")
    private String description;

    private List<Item> items;

    public Invoice(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
