package dev.edmond.swapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.FilmMapper;
import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.repository.FilmRepository;
import dev.edmond.swapi.web.dto.FilmDto;

@RestController
@RequestMapping(value = "/swapi/films")
public class FilmController {

@Autowired
private FilmRepository filmRepo;

@Autowired
private FilmMapper filmMapper;


    @PostMapping(value = "")
    public ResponseEntity<FilmDto> postFilm (@RequestBody FilmDto request){
        
        Film film = filmMapper.filmFromDto(request);
        Film savedFilm = filmRepo.save(film);
        FilmDto response = filmMapper.dtoFromFilm(savedFilm);

        return ResponseEntity.ok().body(response);
    } 
    
}
