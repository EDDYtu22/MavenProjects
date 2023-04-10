package dev.edmond.swapi.web.dto;

import java.util.Set;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {

    private String name;

    private long height;

    private long mass;

    private String hair_color;

    private String eye_color;

    private String skin_color;

    private String birth_year;

    private String gender;

    private Planet planet;

    private Set<Film> films;

    private Set<Specie> species;

    private Set<Vehicle> vehicles;

    private Set<Starship> starships;

    private String created;

    private String updated;

    private String url;
}
