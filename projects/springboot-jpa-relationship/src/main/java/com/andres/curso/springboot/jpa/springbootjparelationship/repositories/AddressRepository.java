package com.andres.curso.springboot.jpa.springbootjparelationship.repositories;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
