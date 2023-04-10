package dev.edmond.swapi.models;

import  java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String url;

}
