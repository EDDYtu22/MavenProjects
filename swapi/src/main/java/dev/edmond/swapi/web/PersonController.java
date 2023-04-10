package dev.edmond.swapi.web;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.PersonMapper;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.repository.PersonRepository;
import dev.edmond.swapi.web.dto.PersonCreateRequest;
import dev.edmond.swapi.web.dto.PersonResponse;

@RestController
@RequestMapping("/swapi/persons")
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepo;

   

    @PostMapping(value = "")
    public ResponseEntity<PersonResponse> postPerson(@RequestBody PersonCreateRequest request) {

        Person person = personMapper.personFromRequest(request);
        Person savedPerson = personRepo.save(person);
        PersonResponse response = personMapper.responseFromPerson(savedPerson);

        return ResponseEntity.ok().body(response);

    }

    // @PostMapping(value = "")
    // public ResponseEntity<List<PersonResponse>> postAllPersons(@RequestBody List<PersonCreateRequest> requestList){

    //     List<Person> personList = new ArrayList<>();
    //     personList =  personMapper.personListFromRequestList(requestList);
        
    //     List<PersonResponse> responseList = new ArrayList<>();
    //     for(Person person : personList){
    //         Person savedPerson = personRepo.save(person);
    //         PersonResponse response = personMapper.responseFromPerson(savedPerson);
    //         responseList.add(response);
    //     }

    //     return ResponseEntity.ok().body(responseList);
    // }
}
