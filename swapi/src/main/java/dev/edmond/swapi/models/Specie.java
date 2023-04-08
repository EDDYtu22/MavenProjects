package dev.edmond.swapi.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String classification;

    private String destination;

    private String avgHeight;

    private String skinColors;

    private String hairColors;

    private String eyeColors;

    private String avfLifespan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Planet_ID")
    private Planet planet;
    
    private String lenguage;

    @ManyToMany(mappedBy = "species")
    private Set<Person> persons;

    @ManyToMany(mappedBy = "species")
    private Set<Film> films;

    private String created;

    private String edited;

    private String url;

}
