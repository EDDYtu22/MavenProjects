package dev.edmond.swapi.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.web.dto.StarshipDto;
import dev.edmond.swapi.web.dto.StarshipResponse;

@Mapper
public interface StarshipMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "pilots", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public Starship starshipFromDto (StarshipDto dto);

    public StarshipDto dtoFromStarship (Starship starship);

    public StarshipResponse responseFromStarship(Starship starship);


    public static Set<String> personsUrlsFromStarship(Set<Person> pilots){

        Set<String> personUrls = new HashSet<>();

        for(Person person : pilots){
            personUrls.add("http://localhost:8080/swapi/persons/" + person.getId());
        }

        return personUrls;

    }

    public static Set<String> filmUrlsFromStarship(Set<Film> films){
        Set<String> filmUrls = new HashSet<>();

        for(Film film : films){
            filmUrls.add("http://localhost:8080/swapi/films/" + film.getId());
        }

        return filmUrls;

    }

    public List<Starship> starshipListFromRequestList(List<StarshipDto> requestList);

}
