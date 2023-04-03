package dev.edmond.springstart.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.edmond.springstart.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {

}
