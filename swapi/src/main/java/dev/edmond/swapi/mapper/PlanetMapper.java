package dev.edmond.swapi.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.web.dto.PlanetCreateRequest;
import dev.edmond.swapi.web.dto.PlanetResponse;

@Mapper
public interface PlanetMapper {
    

    // mapping PlanetCreateRequest to Planet
    @Mapping(target = "residents", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "species", ignore = true)
    public Planet planetFromRequest(PlanetCreateRequest request);

    // mapping Planet to PlanetResponse
    public PlanetResponse responseFromPlanet(Planet planet);

    public static Set<String> personUrlsFromPlanet(Set<Person> persons){
        Set<String> personUrls = new HashSet<>();

        for(Person person : persons){
            personUrls.add("http://localhost:8080/swapi/persons/" + person.getId());
        }

        return personUrls;

    }
    public static Set<String> filmUrlsFromPlanet(Set<Film> films){
        Set<String> filmUrls = new HashSet<>();

        for(Film film : films){
            filmUrls.add("http://localhost:8080/swapi/films/" + film.getId());
        }

        return filmUrls;

    }

    public List<Planet> planetListFromRequestList(List<PlanetCreateRequest> requestList);

    
}
