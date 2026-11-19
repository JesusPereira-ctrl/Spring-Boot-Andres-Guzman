package com.andres.curso.springboot.di.factura.springbootdifactura;

import com.andres.curso.springboot.di.factura.springbootdifactura.models.Item;
import com.andres.curso.springboot.di.factura.springbootdifactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean("default")
    List<Item> itemsInvoiceSport() {
        return Arrays.asList(
                new Item(new Product("Camara Sony", 800), 2),
                new Item(new Product("Bicicleta Bianchi 26", 1200), 4)
        );
    }

    @Bean
    List<Item> itemsInvoiceOffice() {
        return Arrays.asList(
                new Item(new Product("Monitor Asus 24", 700), 4),
                new Item(new Product("Notebook Razer", 2400), 6),
                new Item(new Product("Impresora HP", 800), 1),
                new Item(new Product("Escritorio Oficina", 900), 4)
        );
    }
}
