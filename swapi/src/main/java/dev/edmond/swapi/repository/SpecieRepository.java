package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;

import dev.edmond.swapi.models.Specie;

public interface SpecieRepository extends CrudRepository<Specie, Integer> {
    
}
