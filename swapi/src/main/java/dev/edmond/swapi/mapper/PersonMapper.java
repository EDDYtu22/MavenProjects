package dev.edmond.swapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.web.dto.PersonCreateRequest;
import dev.edmond.swapi.web.dto.PersonResponse;

@Mapper
public interface PersonMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "species", ignore = true)
    @Mapping(target = "starships", ignore = true)
    @Mapping(target = "planet", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "vehicles", ignore = true)
    public Person personFromRequest(PersonCreateRequest request);

    public PersonResponse responseFromPerson(Person person);

    public List<Person> personListFromRequestList(List<PersonCreateRequest> requestList);

}
