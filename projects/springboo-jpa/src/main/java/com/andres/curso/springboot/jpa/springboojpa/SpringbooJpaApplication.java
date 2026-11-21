package com.andres.curso.springboot.jpa.springboojpa;

import com.andres.curso.springboot.jpa.springboojpa.entities.Person;
import com.andres.curso.springboot.jpa.springboojpa.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbooJpaApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    public SpringbooJpaApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbooJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> persons = (List<Person>) this.personRepository.findAll();

        persons.stream().forEach(person -> {
            System.out.println(person);
        });
    }
}
