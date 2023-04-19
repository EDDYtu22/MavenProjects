package dev.edmond.swapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.web.dto.SpecieDto;

@Mapper
public interface SpecieMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "persons", ignore = true)
    @Mapping(target = "planet", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public Specie specieFromDto (SpecieDto dto);

    public SpecieDto dtoFromSpecie(Specie specie);

    public  List<Specie> specieListFromDtoList (List<SpecieDto> requestList);
    
}
