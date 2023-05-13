package dev.edmond.swapi.web.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PlanetResponse {
 
    private Integer id;

    private String name;

    private String rotation_period;

    private String orbital_period;

    private String diameter;

    private String climate;

    private String gravity;

    private String terrain;

    private String surface_water;

    private String population;
    
    
    private Set<String> residents;

    
    private Set<String> films;

    private String url;
    
}
