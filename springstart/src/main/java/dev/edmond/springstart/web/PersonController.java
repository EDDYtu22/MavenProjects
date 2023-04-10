package dev.edmond.springstart.web;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.springstart.error.InvalidObjectException;
import dev.edmond.springstart.error.NotFoundObjectException;
import dev.edmond.springstart.mapper.PersonMapper;
import dev.edmond.springstart.models.Person;
import dev.edmond.springstart.models.Photo;
import dev.edmond.springstart.repository.PersonPagingRepository;
import dev.edmond.springstart.repository.PersonRepository;
import dev.edmond.springstart.repository.PhotoRepository;
import dev.edmond.springstart.validation.ObjectValidator;
import dev.edmond.springstart.web.dto.PersonCreateRequest;
import dev.edmond.springstart.web.dto.PersonPhotosResponse;
import dev.edmond.springstart.web.dto.PersonResponse;
import dev.edmond.springstart.web.dto.PersonUpdateRequest;
import dev.edmond.springstart.web.dto.SetPersonPhotosRequest;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @Autowired
    private PersonPagingRepository pagingRepo;

    @Autowired
    private PhotoRepository photoRepo;

    @Autowired
    private ObjectValidator validator;

    @Autowired
    private PersonMapper personMapper;

    private final Integer PAGE_SIZE = 5;

    @GetMapping("")
    public List<PersonResponse> getAllPersons() {
        //@RequestParam(defaultValue = "1") Integer currPage
        //return pagingRepo.findAll(PageRequest.of(currPage, PAGE_SIZE)).map(personMapper::personResponseFromPerson);

        //Page<Person>
        //Page<PersonResponse>
        

        return personMapper.listOfModelToListOfDto(repo.findAll());
        
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponse> getPersonByID(@PathVariable String personId) {
        Person person = repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found!", Person.class.getName(), personId);
        });

        return ResponseEntity.ok().body(personMapper.personResponseFromPerson(person));

    }

    @GetMapping("/{personId}/photos")
    public PersonPhotosResponse getAllPersonPhotos(@PathVariable String personId) {
        Person person = repo.findById(UUID.fromString(personId)).get();

        Set<UUID> allPhotosIds = new HashSet<>();
        for (Photo photo : person.getPhotos()) {
            allPhotosIds.add(photo.getId());
        }

        PersonPhotosResponse response = new PersonPhotosResponse();
        response.setPersonPhotoIds(allPhotosIds);
        return response;
    }

    @PutMapping(value = "{personId}/photos")
    public PersonPhotosResponse setPersonPhotos(@PathVariable String personId,
            @RequestBody SetPersonPhotosRequest request) {
        Person person = repo.findById(UUID.fromString(personId)).get();

        Map<String, String> validationErrors = validator.validate(request);

        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Photos Upsert Request", validationErrors);
        }

        List<Photo> allPersonPhotos = (List<Photo>) photoRepo.findAllById(request.getPersonPhotoIds());

        person.setPhotos(new HashSet<>(allPersonPhotos));
        Person savedPerson = repo.save(person);

        Set<UUID> allPhotosIds = new HashSet<>();
        for (Photo photo : savedPerson.getPhotos()) {
            allPhotosIds.add(photo.getId());
        }

        PersonPhotosResponse response = new PersonPhotosResponse();
        response.setPersonPhotoIds(allPhotosIds);
        return response;
    }

    @PatchMapping("/{personId}")
    ResponseEntity<PersonResponse> updatePerson(@PathVariable String personId,
            @RequestBody PersonUpdateRequest personDto) {

        Map<String, String> validationErrors = validator.validate(personDto);

        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Photos Upsert Request", validationErrors);
        }

        Person currentPerson =  repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found!", Person.class.getName(), personId);
        });

    personMapper.updateModelFromDto(personDto, currentPerson);

    Person updatedPerson = repo.save(currentPerson);

    PersonResponse responsePerson = personMapper.personResponseFromPerson(updatedPerson);

    return ResponseEntity.status(200).body(responsePerson);

    }

    @DeleteMapping("/{personId}")
    public void deletePersonByID(@PathVariable String personId) {
        repo.deleteById(UUID.fromString(personId));
    }

    @PostMapping(value = "")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonCreateRequest personDto) {

        Map<String, String> validationErrors = validator.validate(personDto);

        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }

        // DTO -> Person Model

        Person mappedPerson = personMapper.modelFromCreateRequest(personDto);

        Person savedPerson = repo.save(mappedPerson);

        PersonResponse response = personMapper.personResponseFromPerson(savedPerson);
        return ResponseEntity.status(201).body(response);
    }

}
