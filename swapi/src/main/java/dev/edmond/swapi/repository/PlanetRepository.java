package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;

import dev.edmond.swapi.models.Planet;

public interface PlanetRepository extends CrudRepository<Planet, Integer> {
    
}
