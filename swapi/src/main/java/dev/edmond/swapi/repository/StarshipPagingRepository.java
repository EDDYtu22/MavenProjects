package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.edmond.swapi.models.Starship;

public interface StarshipPagingRepository extends PagingAndSortingRepository<Starship, Integer>{
    
}
