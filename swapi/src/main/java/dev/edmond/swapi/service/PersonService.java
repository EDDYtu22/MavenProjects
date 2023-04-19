package dev.edmond.swapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.error.ObjectNotFoundException;
import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.repository.FilmRepository;
import dev.edmond.swapi.repository.PersonPagingRepository;
import dev.edmond.swapi.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired 
    private PersonRepository repo;
   
    @Autowired 
    private FilmRepository filmRepo;

    @Autowired
    private PersonPagingRepository pagingRepo;


    public Person save(Person person) {
        return repo.save(person);
    }

    public Page<Person> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Set<Integer> setPersonPhotos(String personId, Set<Integer> personFilmIds) {
        Person person = repo.findById(Integer.parseInt(personId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Person Not Found", Person.class.getName(), personId);
        });

        List<Film> allPersonFilms =
                (List<Film>) filmRepo.findAllById(personFilmIds);

        person.setFilms(new HashSet<>(allPersonFilms));
        Person savedPerson = repo.save(person);

        Set<Integer> allPersonFilmIds = new HashSet<>();
        for (Film film : savedPerson.getFilms()) {
            allPersonFilmIds.add(film.getId());
        }

        return allPersonFilmIds;
    }
    
}
