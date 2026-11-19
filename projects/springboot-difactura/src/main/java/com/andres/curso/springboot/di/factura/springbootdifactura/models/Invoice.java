package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Invoice {

    private final Client client;

    @Value("${invoice.description.sport}")
    private String description;

    private final List<Item> items;

    public Invoice(Client client, @Qualifier("default") List<Item> items) {
        this.client = client;
        this.items = items;
        System.out.println("Creando el componete de la factura");
    }

    @PostConstruct
    public void init() {
        System.out.println("Componente de la factura creado");
        client.setName(client.getName().concat(" Pepe"));
        description = description.concat(" del cliente: " + client.getName().concat(" ").concat(client.getLastName()));
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
