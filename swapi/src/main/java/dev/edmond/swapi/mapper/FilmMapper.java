package dev.edmond.swapi.mapper;

import org.mapstruct.Mapper;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.web.dto.FilmDto;

@Mapper
public interface FilmMapper {
    
    public Film filmFromDto(FilmDto dto);
    public FilmDto dtoFromFilm(Film film);
}
