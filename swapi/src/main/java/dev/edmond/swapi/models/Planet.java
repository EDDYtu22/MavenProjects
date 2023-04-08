package dev.edmond.swapi.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Builder
public class Planet {
    
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String rotationPeriod;

    private String orbitationalPeriod;

    private String diameter;

    private String climate;

    private String gravity;

    private String terrain;

    private String surfaceWater;

    private String population;

    @OneToMany(mappedBy = "planet")
    private Set<Person> residents;

    @OneToMany(mappedBy = "planet")
    private Set<Specie> species;

    @ManyToMany(mappedBy = "planets")
    private Set<Film> films;

    private String created;

    private String edited;

    private String url;

}
