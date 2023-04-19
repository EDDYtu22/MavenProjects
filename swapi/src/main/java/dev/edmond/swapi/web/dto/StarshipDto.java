package dev.edmond.swapi.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarshipDto {
    
    private String name;

    private String model;

    private String manufacturer;
    
    private String cost_in_credits;
    
    private String length;

    private String max_atmosphering_speed;

    private String crew;

    private String passengers;

    private String cargo_capacity;

    private String consumables;

    private String hyperdrive_rating;

    private String MGLT;

    private String starship_class;

    private String url;
}
