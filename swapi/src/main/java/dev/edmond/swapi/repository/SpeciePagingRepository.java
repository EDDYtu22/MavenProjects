package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.edmond.swapi.models.Specie;

public interface SpeciePagingRepository extends PagingAndSortingRepository<Specie, Integer>{
    
}
