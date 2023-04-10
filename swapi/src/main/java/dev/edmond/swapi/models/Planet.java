package dev.edmond.swapi.models;

import  java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Planet {
    
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "rotation_period")
    private String rotation_period;

    @Column(name = "orbital_period")
    private String orbital_period;

    @Column(name = "diameter")
    private String diameter;

    @Column(name = "climate")
    private String climate;

    @Column(name = "gravity")
    private String gravity;

    @Column(name = "tarrain")
    private String terrain;

    @Column(name = "surface_water")
    private String surface_water;

    @Column(name = "population")
    private String population;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL)
    private Set<Person> residents;

    @OneToMany(mappedBy = "planet")
    private Set<Specie> species;

    @ManyToMany(mappedBy = "planets", cascade = CascadeType.ALL)
    private Set<Film> films;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "edited")
    @UpdateTimestamp
    private LocalDateTime edited;

    @Column(name = "url")
    private String url;
 }
