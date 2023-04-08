package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;


import dev.edmond.swapi.models.Person;

public interface PersonPagingRepository extends PagingAndSortingRepository<Person, Integer> {
    
}
