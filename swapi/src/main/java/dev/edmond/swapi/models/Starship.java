package dev.edmond.swapi.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Starship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String model;

    private String manufacturer;

    private String cost;

    private String length;

    private String maxSpeed;

    private String crew;

    private String passangers;

    private String cargoCapacity;

    private String consumables;

    private String vehicleClass;

    @ManyToMany(mappedBy = "starships")
    private Set<Person> pilots;

    @ManyToMany(mappedBy = "starships")
    private Set<Film> films;

    private String created;

    private String edited;

    private String url;

}
