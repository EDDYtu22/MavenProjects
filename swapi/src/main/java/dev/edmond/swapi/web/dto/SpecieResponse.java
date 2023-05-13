package dev.edmond.swapi.web.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecieResponse {
    
    @JsonIgnore
    private Integer id;

    private String name;

    private String classification;

    private String designation;

    private String average_height;

    private String skin_colors;

    private String hair_colors;

    private String eye_colors;

    private String average_lifespan;

    private String planet;
   
    private String language;

    private Set<String> persons;

    private Set<String> films;

    private String created;

    private String updated;

    private String url;
}
