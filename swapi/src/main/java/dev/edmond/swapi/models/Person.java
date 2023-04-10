package dev.edmond.swapi.models;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import  java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private Integer id;

    private String name;

    private long height;

    private long mass;

    private String hair_color;

    private String eye_color;

    private String skin_color;

    private String birth_year;

    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Planet_ID")
    private Planet planet;

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

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private String url;

}
