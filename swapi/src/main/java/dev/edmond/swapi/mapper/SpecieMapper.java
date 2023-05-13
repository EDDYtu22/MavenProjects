package dev.edmond.swapi.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.web.dto.SpecieDto;
import dev.edmond.swapi.web.dto.SpecieResponse;

@Mapper
public interface SpecieMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "persons", ignore = true)
    @Mapping(target = "planet", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public Specie specieFromDto (SpecieDto dto);

    public SpecieDto dtoFromSpecie(Specie specie);

    public SpecieResponse responseFromSpecie(Specie specie);

    public static Set<String> filmUrlsFromSpecie(Set<Film> films){
        Set<String> filmUrls = new HashSet<>();

        for(Film film : films){
            filmUrls.add("http://localhost:8080/swapi/films/" + film.getId());
        }

        return filmUrls;

    }

    public static Set<String> personUrlsFromSpecie(Set<Person> persons){
        Set<String> personUrls = new HashSet<>();

        for(Person person : persons){
            personUrls.add("http://localhost:8080/swapi/persons/" + person.getId());
        }

        return personUrls;
    }

    public static String planetUrlFromSpecie(Planet planet){
        if (planet != null){
            return "http://localhost:8080/swapi/planets/" + planet.getId();

        }
        return null;
    }

    public  List<Specie> specieListFromDtoList (List<SpecieDto> requestList);
    
}
