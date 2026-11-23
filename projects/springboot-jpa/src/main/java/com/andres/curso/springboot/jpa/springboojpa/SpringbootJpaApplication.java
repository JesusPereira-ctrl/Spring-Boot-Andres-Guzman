package com.andres.curso.springboot.jpa.springboojpa;

import com.andres.curso.springboot.jpa.springboojpa.dto.PersonDto;
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
        queriesFunctionAggregation();
    }

    @Transactional(readOnly = true)
    public void queriesFunctionAggregation() {
        System.out.println("========== consulta con el total de registros de la tabla persona ==========");
        Long count = this.personRepository.getTotalPerson();
        System.out.println(count);

        System.out.println("========== consulta con el valor mínimo del id ==========");
        Long min = this.personRepository.getMinId();
        System.out.println(min);

        System.out.println("========== consulta con el valor máximo del id ==========");
        Long max = this.personRepository.getMaxId();
        System.out.println(max);

        System.out.println("========== consulta con el nombre y su largo ==========");
        List<Object[]> regs = this.personRepository.getPersonNameLength();
        regs.forEach(reg -> {
            String name = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("name=" + name + ", length=" + length);
        });

        System.out.println("========== consulta con el nombre mas corto ==========");
        Integer minLengthName = this.personRepository.getMinLengthName();
        System.out.println(minLengthName);

        System.out.println("========== consulta con el nombre mas largo ==========");
        Integer maxLengthName = this.personRepository.getMaxLengthName();
        System.out.println(maxLengthName);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("========== consulta por rangos ==========");
        List<Person> persons = this.personRepository.findByIdBetweenOrderByNameAsc(2L, 5L);
        persons.forEach(System.out::println);

        persons = this.personRepository.findByNameBetweenOrderByNameDescLastNameDesc("J", "Q");
        persons.forEach(System.out::println);

        persons = this.personRepository.findAllByOrderByNameAscLastNameDesc();
        persons.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase() {
        System.out.println("========== consulta nombres y apellidos de personas ==========");
        List<String> names = this.personRepository.findAllFullNameConcat();
        names.forEach(System.out::println);

        System.out.println("========== consulta nombres y apellidos mayúscula ==========");
        names = this.personRepository.findAllFullNameConcatUpper();
        names.forEach(System.out::println);

        System.out.println("========== consulta nombres y apellidos minúscula ==========");
        names = this.personRepository.findAllFullNameConcatLower();
        names.forEach(System.out::println);

        System.out.println("========== consulta personalizada persona upper y lower case ==========");
        List<Object[]> regs = this.personRepository.findAllPersonDataListCase();
        regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        System.out.println("========== consulta con nombres de personas ==========");
        List<String> names = this.personRepository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("========== consulta con nombres únicos de personas ==========");
        names = this.personRepository.findAllNamesDistinct();
        names.forEach(System.out::println);

        System.out.println("========== consulta con lenguajes de programación únicos ==========");
        List<String> languages = this.personRepository.findAllProgrammingLanguagesDistinct();
        languages.forEach(System.out::println);

        System.out.println("========== consulta con total de lenguajes de programación únicos ==========");
        Long totalLanguage = this.personRepository.findAllProgrammingLanguagesDistinctCount();
        System.out.println("Total de lenguajes de programación: " + totalLanguage);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println("========== consulta por objeto persona y lenguaje de programación ==========");
        List<Object[]> personRegs = this.personRepository.findAllMixPerson();

        personRegs.forEach(reg -> {
            System.out.println("programmingLanguage=" + reg[1] + ", person=[" + reg[0] + "]");
        });

        System.out.println("========== consulta que puebla y devuelve objeto entity de una instancia personalizada ==========");
        List<Person> persons = this.personRepository.findAllObjectPersonPersonalized();
        persons.forEach(System.out::println);

        System.out.println("========== consulta que puebla y devuelve objeto dto de una clase personalizada ==========");
        List<PersonDto> personsDto = this.personRepository.findAllPersonDto();
        personsDto.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== consulta solo el nombre por el id ==========");
        System.out.println("Ingrese el id:");
        Long id = scanner.nextLong();
        scanner.close();

        System.out.println("========== mostrando solo el nombre ==========");
        String name = this.personRepository.getNameById(id);
        System.out.println(name);

        System.out.println("========== mostrando solo el id ==========");
        Long idDb = this.personRepository.getIdById(id);
        System.out.println(idDb);

        System.out.println("========== mostrando nombre completo con concat ==========");
        String fullName = this.personRepository.getFullNameById(id);
        System.out.println(fullName);

        System.out.println("========== consulta por campos personalizados por el id ==========");
        Optional<Object> optionalReg = this.personRepository.obtenerPersonDataById(id);
        if (optionalReg.isPresent()) {
            Object[] personReg = (Object[]) optionalReg.orElseThrow();
            System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2] + ", lenguaje=" + personReg[3]);
        }

        System.out.println("========== consulta por campos personalizados lista ==========");
        List<Object[]> regs = this.personRepository.obtenerPersonDataList();
        regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
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
