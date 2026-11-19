package com.andres.curso.springboot.di.factura.springbootdifactura.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
// @JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    private final Logger logger = LoggerFactory.getLogger(Invoice.class);

    @Autowired
    private Client client;

    @Value("${invoice.description.sport}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

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

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonProperty
    public int getTotal() {
        return items.stream()
                .map(Item::getImporte)
                .reduce(0, Integer::sum);
    }
}
