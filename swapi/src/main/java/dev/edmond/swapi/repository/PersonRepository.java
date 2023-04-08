package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.edmond.swapi.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    
}
