package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;

import dev.edmond.swapi.models.Starship;

public interface StarshipRepository extends CrudRepository<Starship, Integer>{
    
}
