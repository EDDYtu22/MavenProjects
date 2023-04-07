package dev.edmond.swapi.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Film {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String openingCrawl;

    private String director;

    private String producer;

    private String date;

    @ManyToMany(mappedBy = "films")
    private Set<Person> characters;

    @ManyToMany
    @JoinTable(
        name = "film_planet", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "planet_id"))
    private Set<Planet> planets;

    @ManyToMany
    @JoinTable(
        name = "film_starship", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "starship_id"))
    private Set<Starship> starships;

    @ManyToMany
    @JoinTable(
        name = "film_vehicle", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

    @ManyToMany
    @JoinTable(
        name = "film_spezie", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "spezie_id"))
    private Set<Specie> species;

    private String created;

    private String edited;

    private String url;

    
}
