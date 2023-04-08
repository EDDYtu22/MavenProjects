package dev.edmond.swapi.web.dto;

import java.util.Set;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.validation.ValidHeight;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCreateRequest {

    private String name;

    @ValidHeight
    private long height;

    private long mass;

    private String hairColor;

    private String eyeColor;

    private String skinColor;

    private String birthYear;

    private String gender;

    private String planetName;

    private Set<Film> films;

    private Set<Specie> species;

    private Set<Vehicle> vehicles;

    private Set<Starship> starships;
}
