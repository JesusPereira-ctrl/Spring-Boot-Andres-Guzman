package com.andres.curso.springboot.jpa.springboojpa.repositories;

import com.andres.curso.springboot.jpa.springboojpa.dto.PersonDto;
import com.andres.curso.springboot.jpa.springboojpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN 'J' AND 'Q'")
    List<Person> findAllBetweenName();

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN 2 AND 5")
    List<Person> findAllBetweenId();

    @Query("SELECT p.id, UPPER(p.name), LOWER(p.lastName), UPPER(p.programmingLanguage) FROM Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("SELECT UPPER(p.name || ' ' || p.lastName) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("SELECT LOWER(CONCAT(p.name, ' ', p.lastName)) FROM Person p")
    List<String> findAllFullNameConcatLower();

    // @Query("SELECT CONCAT(p.name, ' ', p.lastName) FROM Person p")
    @Query("SELECT p.name || ' ' || p.lastName FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long findAllProgrammingLanguagesDistinctCount();

    @Query("SELECT DISTINCT(p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammingLanguagesDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT new com.andres.curso.springboot.jpa.springboojpa.dto.PersonDto(p.name, p.lastName) FROM Person p")
    List<PersonDto> findAllPersonDto();

    @Query("SELECT new Person(p.name, p.lastName) FROM Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("SELECT p.name FROM Person p WHERE p.id = ?1")
    String getNameById(Long id);

    @Query("SELECT p.id FROM Person p WHERE p.id = ?1")
    Long getIdById(Long id);

    @Query("SELECT CONCAT(p.name, ' ', p.lastName) AS fullName FROM Person p WHERE p.id = ?1")
    String getFullNameById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPerson();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Optional<Object> obtenerPersonDataById(Long id);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);
}
