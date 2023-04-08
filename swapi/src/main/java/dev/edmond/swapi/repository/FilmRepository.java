package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;

import dev.edmond.swapi.models.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    
}
