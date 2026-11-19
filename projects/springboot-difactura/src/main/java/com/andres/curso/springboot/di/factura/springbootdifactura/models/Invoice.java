package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    private final Logger logger = LoggerFactory.getLogger(Invoice.class);

    private final Client client;

    @Value("${invoice.description.sport}")
    private String description;

    private final List<Item> items;

    public Invoice(Client client, @Qualifier("default") List<Item> items) {
        this.client = client;
        this.items = items;
        logger.warn("Creando el componete de la factura");
    }

    @PostConstruct
    public void init() {
        logger.warn("Componente de la factura creado");
        client.setName(client.getName().concat(" Pepe"));
        description = description.concat(" del cliente: " + client.getName().concat(" ").concat(client.getLastName()));
    }

    @PreDestroy
    public void destroy() {
        logger.warn("Previo a destruir el componente de la factura");
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
