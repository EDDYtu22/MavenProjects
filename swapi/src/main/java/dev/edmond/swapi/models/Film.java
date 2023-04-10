package dev.edmond.swapi.models;

import  java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
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
public class Film {
    
    @Id
    private Integer id;

    private String title;

    @Lob
    private String opening_crawl;

    private String director;

    private String producer;

    private String release_date;

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

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private String url;

    
}
