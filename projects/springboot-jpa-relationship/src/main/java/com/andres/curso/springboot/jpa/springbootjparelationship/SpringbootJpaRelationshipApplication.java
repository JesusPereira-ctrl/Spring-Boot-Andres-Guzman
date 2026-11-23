package com.andres.curso.springboot.jpa.springbootjparelationship;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.andres.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.andres.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final InvoiceRepository invoiceRepository;

    public SpringbootJpaRelationshipApplication(ClientRepository clientRepository, InvoiceRepository invoiceRepository) {
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        manyToOneFindByIdClient();
    }

    public void manyToOneCreateClient() {
        Client client = new Client("John", "Doe");
        client = this.clientRepository.save(client);

        Invoice invoice = new Invoice("Compras De Oficina", 2000L);
        invoice.setClient(client);

        invoice = this.invoiceRepository.save(invoice);
        System.out.println(invoice);
    }

    public void manyToOneFindByIdClient() {
        Optional<Client> optionalClient = this.clientRepository.findById(1L);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.orElseThrow();

            Invoice invoice = new Invoice("Compras De Oficina", 2000L);
            invoice.setClient(client);

            invoice = this.invoiceRepository.save(invoice);
            System.out.println(invoice);
        }
    }
}
