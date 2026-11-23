package com.andres.curso.springboot.jpa.springbootjparelationship.repositories;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
