package dev.edmond.swapi.web.dto;

import java.util.Set;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetResponse {
 
    private String name;

    private String rotation_period;

    private String orbital_period;

    private String diameter;

    private String climate;

    private String gravity;

    private String terrain;

    private String surface_water;

    private String population;
    
    private Set<Person> residents;

    private Set<Film> films;

    private String created;

    private String edited;

    private String url;
    
}
