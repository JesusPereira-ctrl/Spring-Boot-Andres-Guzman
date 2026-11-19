package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Invoice {

    private final Client client;

    @Value("${invoice.description}")
    private String description;

    private final List<Item> items;

    public Invoice(Client client, List<Item> items) {
        this.client = client;
        this.items = items;
    }

    @JsonProperty
    public Client getClient() {
        return client;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty
    public int getTotal() {
        return items.stream()
                .map(Item::getImporte)
                .reduce(0, Integer::sum);
    }
}
