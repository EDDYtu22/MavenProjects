package dev.edmond.swapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.web.dto.PlanetCreateRequest;
import dev.edmond.swapi.web.dto.PlanetResponse;

@Mapper
public interface PlanetMapper {
    

    // mapping PlanetCreateRequest to Planet
    @Mapping(target = "residents", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "species", ignore = true)
    public Planet planetFromRequest(PlanetCreateRequest request);

    // mapping Planet to PlanetResponse
    public PlanetResponse responseFromPlanet(Planet planet);

    public List<Planet> planetListFromRequestList(List<PlanetCreateRequest> requestList);

    
}
