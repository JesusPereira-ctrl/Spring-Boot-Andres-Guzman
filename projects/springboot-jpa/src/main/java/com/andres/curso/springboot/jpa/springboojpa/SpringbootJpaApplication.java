package com.andres.curso.springboot.jpa.springboojpa;

import com.andres.curso.springboot.jpa.springboojpa.entities.Person;
import com.andres.curso.springboot.jpa.springboojpa.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    public SpringbootJpaApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        create();
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre:");
        String name = scanner.next();

        System.out.println("Ingrese el apellido:");
        String lastName = scanner.next();

        System.out.println("Ingrese el lenguaje de programación:");
        String programmingLanguage = scanner.next();
        
        scanner.close();

        Person person = new Person(null, name, lastName, programmingLanguage);

        Person personNew = this.personRepository.save(person);
        System.out.println(personNew);

        this.personRepository.findById(personNew.getId()).ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findOne() {
        // Person person = null;
        // Optional<Person> optionalPerson = this.personRepository.findById(1L);
        // if (!optionalPerson.isEmpty()) {
        // if (optionalPerson.isPresent()) {
        //     person = optionalPerson.get();
        // }
        // System.out.println(person);
        this.personRepository.findOneLikeName("hn").ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void list() {
        // List<Person> persons = (List<Person>) this.personRepository.findAll();
        // List<Person> persons = this.personRepository.buscarByProgrammingLanguage("Python", "Pepe");
        List<Person> persons = this.personRepository.findByProgrammingLanguageAndName("Java", "Andres");

        persons.stream().forEach(person -> {
            System.out.println(person);
        });

        List<Object[]> personValues = this.personRepository.obtenerPersonDataByProgrammingLanguage("Java");
        personValues.stream().forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
    }
}
