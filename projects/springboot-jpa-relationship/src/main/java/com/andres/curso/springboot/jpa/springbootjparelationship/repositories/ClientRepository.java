package com.andres.curso.springboot.jpa.springbootjparelationship.repositories;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.addresses WHERE c.id = ?1")
    Optional<Client> findOneByIdWithAddresses(Long id);

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.invoices WHERE c.id = ?1")
    Optional<Client> findOneByIdWithInvoices(Long id);

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.invoices LEFT JOIN FETCH c.addresses WHERE c.id = ?1")
    Optional<Client> findOneById(Long id);
}
