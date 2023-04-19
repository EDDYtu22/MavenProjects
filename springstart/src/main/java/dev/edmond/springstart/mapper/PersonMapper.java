package dev.edmond.springstart.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import dev.edmond.springstart.models.Person;
import dev.edmond.springstart.web.dto.PersonCreateRequest;
import dev.edmond.springstart.web.dto.PersonResponse;
import dev.edmond.springstart.web.dto.PersonUpdateRequest;

@Mapper(uses = AddressMapper.class)
public interface PersonMapper{
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Person modelFromCreateRequest(PersonCreateRequest request);
    
    PersonResponse responseFromModel(Person person);

    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "egnNumber", ignore = true)
    @Mapping(target = "address", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "age", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(PersonUpdateRequest personUpdateDto, @MappingTarget Person person);


    List<PersonResponse> listOfModelToListOfDto(Iterable<Person> persons);
}