package com.andres.curso.springboot.jpa.springboojpa.repositories;

import com.andres.curso.springboot.jpa.springboojpa.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
