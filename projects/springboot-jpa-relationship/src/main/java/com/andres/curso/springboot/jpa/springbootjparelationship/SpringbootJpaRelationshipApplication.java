package com.andres.curso.springboot.jpa.springbootjparelationship;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.andres.curso.springboot.jpa.springbootjparelationship.repositories.AddressRepository;
import com.andres.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.andres.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final InvoiceRepository invoiceRepository;

    private final AddressRepository addressRepository;

    public SpringbootJpaRelationshipApplication(ClientRepository clientRepository, InvoiceRepository invoiceRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        oneToManyInvoiceBidirectional();
    }

    @Transactional
    public void oneToManyInvoiceBidirectional() {
        Client client = new Client("Fran", "Moras");

        Invoice invoice1 = new Invoice("Compras De La Casa", 5000L);
        Invoice invoice2 = new Invoice("Compras De Oficina", 8000L);

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1);
        invoices.add(invoice2);

        client.setInvoices(invoices);

        invoice1.setClient(client);
        invoice2.setClient(client);

        this.clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void removeAddressFindClient() {
        Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client -> {
            Address address1 = new Address("El vergel", 1234);
            Address address2 = new Address("Vasco de Gama", 9875);

            Address addressSaved1 = this.addressRepository.save(address1);
            Address addressSaved2 = this.addressRepository.save(address2);

            client.setAddresses(Arrays.asList(addressSaved1, addressSaved2));

            this.clientRepository.save(client);

            System.out.println(client);

            Optional<Client> optionalClient2 = this.clientRepository.findOneById(2L);
            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(addressSaved2);
                this.clientRepository.save(c);
                System.out.println(c);
            });
        });
    }

    @Transactional
    public void removeAddress() {
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El vergel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        this.clientRepository.save(client);

        System.out.println(client);

        Optional<Client> optionalClient = this.clientRepository.findById(3L);
        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address1);
            this.clientRepository.save(c);
            System.out.println(c);
        });
    }

    @Transactional
    public void oneToManyFindClient() {
        Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client -> {
            Address address1 = new Address("El vergel", 1234);
            Address address2 = new Address("Vasco de Gama", 9875);

            client.setAddresses(Arrays.asList(address1, address2));

            this.clientRepository.save(client);

            System.out.println(client);
        });
    }

    @Transactional
    public void oneToManyCreateClient() {
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El vergel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        this.clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void manyToOneCreateClient() {
        Client client = new Client("John", "Doe");
        client = this.clientRepository.save(client);

        Invoice invoice = new Invoice("Compras De Oficina", 2000L);
        invoice.setClient(client);

        invoice = this.invoiceRepository.save(invoice);
        System.out.println(invoice);
    }

    @Transactional
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
