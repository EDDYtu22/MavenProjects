package dev.edmond.springstart.web;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.springstart.models.Person;
import dev.edmond.springstart.models.Photo;
import dev.edmond.springstart.repository.PersonRepository;
import dev.edmond.springstart.repository.PhotoRepository;
import dev.edmond.springstart.web.dto.PersonEditResponse;
import dev.edmond.springstart.web.dto.PersonPhotosResponse;
import dev.edmond.springstart.web.dto.PersonResponse;
import dev.edmond.springstart.web.dto.SetPersonPhotosRequest;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @Autowired
    private PhotoRepository photoRepo;

    @Autowired
    private EntityManager eManager;

    @GetMapping("")
    public List<Person> getAllPersons() {
        // System.out.println("test");
        return (List<Person>) repo.findAll();
    }

    @GetMapping("/{personId}")
    public PersonResponse getPersonByID(@PathVariable String personId) {
        PersonResponse response = new PersonResponse();
        response.setPerson(repo.findById(UUID.fromString(personId)).get());
        return response;

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
    public PersonEditResponse patchAtributes(@RequestBody Person person2, @PathVariable String personId) {
        Person person = repo.findById(UUID.fromString(personId)).get();
        PersonEditResponse response = new PersonEditResponse();
        response.setEdits(person.patchAtributes(person2));
        repo.save(person);

        return response;
    }

    @DeleteMapping("/{personId}")
    public void deletePersonByID(@PathVariable String personId) {
        repo.deleteById(UUID.fromString(personId));
    }

    @PostMapping(value = "")
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
        Person newPerson = repo.save(person);
        return ResponseEntity.status(201).body(newPerson);
    }

}
