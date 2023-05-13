package dev.edmond.swapi.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.error.InvalidObjectException;
import dev.edmond.swapi.mapper.PersonMapper;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.service.PersonService;
import dev.edmond.swapi.validation.ObjectValidator;
import dev.edmond.swapi.web.dto.PersonCreateRequest;
import dev.edmond.swapi.web.dto.PersonFilmsResponse;
import dev.edmond.swapi.web.dto.PersonResponse;
import dev.edmond.swapi.web.dto.PersonSpeciesResponse;
import dev.edmond.swapi.web.dto.PersonStarshipsResponse;
import dev.edmond.swapi.web.dto.PersonVehiclesResponse;
import dev.edmond.swapi.web.dto.SetFilmsRequest;
import dev.edmond.swapi.web.dto.SetSpeciesRequest;
import dev.edmond.swapi.web.dto.SetStarshipsRequest;
import dev.edmond.swapi.web.dto.SetVehiclesRequest;
import dev.edmond.swapi.web.dto.SwapiPage;


@RestController
@RequestMapping("/swapi/persons")
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonService personService;

    @Autowired 
    private ObjectValidator validator;

   

    // @PostMapping(value = "")
    // public ResponseEntity<PersonResponse> postPerson(@RequestBody PersonCreateRequest request) {

    //     Map<String, String> validationErrors = validator.validate(request);

    //     if (validationErrors.size() != 0) {
    //         throw new InvalidObjectException("Invalid Person Request Data", validationErrors);
    //     }

    //     Person person = personMapper.personFromRequest(request);
    //     Person savedPerson = personService.save(person);
    //     PersonResponse response = personMapper.responseFromPerson(savedPerson);

    //     return ResponseEntity.ok().body(response);

    // }

    @PostMapping(value = "")
    public List<PersonCreateRequest> postAllPersons(@RequestBody List<PersonCreateRequest> requestList){


        List<Person> personList = new ArrayList<>();
        personList =  personMapper.personListFromRequestList(requestList);
        
        List<PersonCreateRequest> responseList = new ArrayList<>();
        for(Person person : personList){
            Person savedPerson = personService.save(person);
            PersonCreateRequest response = personMapper.requestFromPerson(savedPerson);
            responseList.add(response);
        }

        return responseList;
    }


    @GetMapping(name= "", produces = "application/json")
    public SwapiPage<PersonResponse> getAllPersons(
            @RequestParam(required = false, defaultValue = "1") Integer currPage) {
                Page<PersonResponse> personPage = 
                personService.fetchAll(currPage - 1, 10).map(personMapper::responseFromPerson);

                for(PersonResponse response : personPage.getContent()){
                    response.setUrl("http://localhost:8080/swapi/persons/" + response.getId());
                }

               
            
        return new SwapiPage<>(personPage, "http://localhost:8080/swapi/persons?currPage=" + (currPage + 1), "http://localhost:8080/swapi/persons?currPage=" + (currPage - 1));
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Integer personId){
        Person person = personService.fetchById(personId);
        PersonResponse response = personMapper.responseFromPerson(person);
        response.setUrl("http://localhost:8080/swapi/persons/" + response.getId());

        return ResponseEntity.ok().body(response);
    }

   

    @PutMapping(value = "/{personId}/films")
    public PersonFilmsResponse setAllPersonFilms(@PathVariable String personId, @RequestBody SetFilmsRequest films) {
        
        Set<Integer> personFilms = personService.setPersonFilms(personId, films.getSetFilms());

        PersonFilmsResponse result = PersonFilmsResponse.builder().personFilmIds(personFilms).build();
        
        return result;
    }

    @PutMapping(value = "/{personId}/vehicles")
    public PersonVehiclesResponse setAllPersonVehicles(@PathVariable String personId, @RequestBody SetVehiclesRequest vehicles) {
        
        Set<Integer> personVehicles = personService.setPersonVehicles(personId, vehicles.getSetVehicles());

        PersonVehiclesResponse result = PersonVehiclesResponse.builder().personVehicleIds(personVehicles).build();
        
        return result;
    }


    @PutMapping(value = "/{personId}/species")
    public PersonSpeciesResponse setAllPersonSpecies(@PathVariable String personId, @RequestBody SetSpeciesRequest species) {
        
        Set<Integer> personSpecies = personService.setPersonSpecies(personId, species.getSetSpecies());

        PersonSpeciesResponse result = PersonSpeciesResponse.builder().personSpecieIds(personSpecies).build();

        return result;
    }

    @PutMapping(value = "/{personId}/starships")
    public PersonStarshipsResponse setAllPersonSatrships(@PathVariable String personId, @RequestBody SetStarshipsRequest starships) {
        
        Set<Integer> personStarships = personService.setPersonStarships(personId, starships.getSetStarships());

        PersonStarshipsResponse result = PersonStarshipsResponse.builder().personStarships(personStarships).build();

        return result;
    }
}
