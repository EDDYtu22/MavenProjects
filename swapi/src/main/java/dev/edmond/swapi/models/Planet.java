package dev.edmond.swapi.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder

public class Planet {
    
    @Id
    private Integer id;

    private String rotationPeriod;

    private String orbitationalPeriod;

    private String diameter;

    private String climate;

    private String gravity;

    private String terrain;

    private String surfacewater;

    private String population;

    @OneToMany(mappedBy = "homeWorld")
    private Set<Person> persons;

    @ManyToMany(mappedBy = "planets")
    private Set<Film> films;

    private String created;

    private String edited;

    private String url;


}
