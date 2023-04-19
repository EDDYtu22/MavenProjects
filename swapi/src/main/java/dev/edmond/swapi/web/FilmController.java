package dev.edmond.swapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.FilmMapper;
import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.service.FilmService;
import dev.edmond.swapi.web.dto.FilmDto;
import dev.edmond.swapi.web.dto.SwapiPage;

@RestController
@RequestMapping(value = "/swapi/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmMapper filmMapper;

    @PostMapping(value = "")
    public ResponseEntity<FilmDto> postFilm(@RequestBody FilmDto request) {

        Film film = filmMapper.filmFromDto(request);
        Film savedFilm = filmService.save(film);
        FilmDto response = filmMapper.dtoFromFilm(savedFilm);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(name = "", produces = "application/json")
    public SwapiPage<FilmDto> getAllPersons(
            @RequestParam(required = false, defaultValue = "1") Integer currPage) {
        Page<FilmDto> filmPage = filmService.fetchAll(currPage - 1, 10).map(filmMapper::dtoFromFilm);

        return new SwapiPage<>(filmPage, "http://localhost:8080/swapi/films?currPage=" + (currPage + 1),
                "http://localhost:8080/swapi/films?currPage=" + (currPage - 1));

    }
}
