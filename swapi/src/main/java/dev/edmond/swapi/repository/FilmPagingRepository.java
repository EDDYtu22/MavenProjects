package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.edmond.swapi.models.Film;

public interface FilmPagingRepository extends PagingAndSortingRepository<Film, Integer> {
    
}
