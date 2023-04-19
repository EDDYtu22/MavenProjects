package dev.edmond.swapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.web.dto.StarshipDto;

@Mapper
public interface StarshipMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "pilots", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public Starship starshipFromDto (StarshipDto dto);

    public StarshipDto dtoFromStarship (Starship starship);

    public List<Starship> starshipListFromRequestList(List<StarshipDto> requestList);

}
