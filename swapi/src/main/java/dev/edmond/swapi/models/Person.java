package dev.edmond.swapi.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private long height;

    private long mass;

    private String hairColor;

    private String eyeColor;

    private String skinColor;

    private String birthYear;

    private String gender;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Planet_ID")
    private Planet homeWorld;

    @ManyToMany
    @JoinTable(
        name = "person_film", 
        joinColumns = @JoinColumn(name = "person_id"), 
        inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> films;

    @ManyToMany
    @JoinTable(
        name = "person_spezie", 
        joinColumns = @JoinColumn(name = "person_id"), 
        inverseJoinColumns = @JoinColumn(name = "spezie_id"))
    private Set<Specie> species;

    @ManyToMany
    @JoinTable(
        name = "person_vehicle", 
        joinColumns = @JoinColumn(name = "person_id"), 
        inverseJoinColumns = @JoinColumn(name = "vechicle_id"))
    private Set<Vehicle> vehicles;

    @ManyToMany
    @JoinTable(
        name = "person_starship", 
        joinColumns = @JoinColumn(name = "person_id"), 
        inverseJoinColumns = @JoinColumn(name = "starship_id"))
    private Set<Starship> starships;


    private String created;

    private String edited;

    private String url;

}
