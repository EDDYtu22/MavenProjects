package dev.edmond.swapi.web.dto;

import lombok.Builder;

import lombok.Data;


@Data
@Builder
public class VehicleDto {

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

    private String vehicle_class;

    private String url;
}
