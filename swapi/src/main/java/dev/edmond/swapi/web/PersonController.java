package dev.edmond.swapi.web;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.PersonMapper;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.service.PersonService;
import dev.edmond.swapi.validation.ObjectValidator;
import dev.edmond.swapi.web.dto.PersonCreateRequest;
import dev.edmond.swapi.web.dto.PersonFilmsResponse;
import dev.edmond.swapi.web.dto.PersonResponse;
import dev.edmond.swapi.web.dto.SetPersonFilmsRequest;
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
    //     Person savedPerson = personRepo.save(person);
    //     PersonResponse response = personMapper.responseFromPerson(savedPerson);

    //     return ResponseEntity.ok().body(response);

    // }

    @PostMapping(value = "")
    public List<PersonResponse> postAllPersons(@RequestBody List<PersonCreateRequest> requestList){

        List<Person> personList = new ArrayList<>();
        personList =  personMapper.personListFromRequestList(requestList);
        
        List<PersonResponse> responseList = new ArrayList<>();
        for(Person person : personList){
            Person savedPerson = personService.save(person);
            PersonResponse response = personMapper.responseFromPerson(savedPerson);
            responseList.add(response);
        }

        return responseList;
    }


    @GetMapping(name= "", produces = "application/json")
    public SwapiPage<PersonResponse> getAllPersons(
            @RequestParam(required = false, defaultValue = "1") Integer currPage) {
                Page<PersonResponse> personPage = 
                personService.fetchAll(currPage - 1, 10).map(personMapper::responseFromPerson);
            
        return new SwapiPage<>(personPage, "http://localhost:8080/swapi/persons?currPage=" + (currPage + 1), "http://localhost:8080/swapi/persons?currPage=" + (currPage - 1));
    }


    @PutMapping(value = "/{personId}/films")
    public PersonFilmsResponse setAllPersonFilms(@PathVariable String personId, @RequestBody SetPersonFilmsRequest films) {
        
        Set<Integer> personFilms = personService.setPersonPhotos(personId, films.getSetPersonFilms());

        PersonFilmsResponse result = PersonFilmsResponse.builder().personFilmIds(personFilms).build();
        
        return result;
    }
}
