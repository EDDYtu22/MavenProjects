package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.edmond.swapi.models.Planet;

public interface PlanetPagingRepository extends PagingAndSortingRepository<Planet, Integer> {
    
}
