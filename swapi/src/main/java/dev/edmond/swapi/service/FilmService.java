package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.repository.FilmPagingRepository;
import dev.edmond.swapi.repository.FilmRepository;

@Service
public class FilmService {
    
    @Autowired 
    private FilmRepository repo;

    @Autowired
    private FilmPagingRepository pagingRepo;


    public Film save(Film film) {
        return repo.save(film);
    }

    public Page<Film> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }
}
