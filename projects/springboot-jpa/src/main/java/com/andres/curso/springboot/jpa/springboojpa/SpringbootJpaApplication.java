package com.andres.curso.springboot.jpa.springboojpa;

import com.andres.curso.springboot.jpa.springboojpa.entities.Person;
import com.andres.curso.springboot.jpa.springboojpa.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        personalizedQueries();
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== consulta solo el nombre por el id ==========");
        System.out.println("Ingrese el id para el nombre:");
        Long id = scanner.nextLong();
        scanner.close();

        String name = this.personRepository.getNameById(id);
        System.out.println(name);

        Long idDb = this.personRepository.getIdById(id);
        System.out.println(idDb);

        String fullName = this.personRepository.getFullNameById(id);
        System.out.println(fullName);
    }

    @Transactional
    public void delete2() {
        this.personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar:");
        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = this.personRepository.findById(id);

        optionalPerson.ifPresentOrElse(
                this.personRepository::delete,
                () -> System.out.println("Lo sentimos no existe la persona con ese id!")
        );

        this.personRepository.findAll().forEach(System.out::println);

        scanner.close();
    }

    @Transactional
    public void delete() {
        this.personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar:");
        Long id = scanner.nextLong();
        this.personRepository.deleteById(id);

        this.personRepository.findAll().forEach(System.out::println);

        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona:");
        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = this.personRepository.findById(id);

        // optionalPerson.ifPresent(person -> {
        if (optionalPerson.isPresent()) {
            Person personDb = optionalPerson.orElseThrow();

            System.out.println(personDb);
            System.out.println("Ingrese el lenguaje de programación:");
            String programmingLanguage = scanner.next();
            personDb.setProgrammingLanguage(programmingLanguage);
            Person personUpdated = this.personRepository.save(personDb);
            System.out.println(personUpdated);
        } else {
            System.out.println("El usuario no esta presente! no existe!");
        }
        // });

        scanner.close();
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
