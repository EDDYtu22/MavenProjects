package dev.edmond.springstart.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dev.edmond.springstart.models.Person;

@Repository
public interface PersonPagingRepository extends PagingAndSortingRepository<Person, UUID> {
    
}
