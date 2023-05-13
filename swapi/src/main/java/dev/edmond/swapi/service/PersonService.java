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
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.repository.FilmRepository;
import dev.edmond.swapi.repository.PersonPagingRepository;
import dev.edmond.swapi.repository.PersonRepository;
import dev.edmond.swapi.repository.SpecieRepository;
import dev.edmond.swapi.repository.StarshipRepository;
import dev.edmond.swapi.repository.VehicleRepository;

@Service
public class PersonService {

    @Autowired 
    private PersonRepository repo;

    @Autowired 
    private VehicleRepository vehicleRepo;

    @Autowired 
    private SpecieRepository specieRepo;

    @Autowired
    private StarshipRepository starshipRepo;
   
   
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

    public Person fetchById(Integer personId){
        Person person = repo.findById(personId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Person Not Found", Person.class.getName(), personId.toString());
        });
        return person;
    }

    public Set<Integer> setPersonFilms(String personId, Set<Integer> personFilmIds) {
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

    public Set<Integer> setPersonVehicles(String personId, Set<Integer> personVehicleIds) {
        Person person = repo.findById(Integer.parseInt(personId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Person Not Found", Person.class.getName(), personId);
        });

        List<Vehicle> allPersonvehicles =
                (List<Vehicle>) vehicleRepo.findAllById(personVehicleIds);

        person.setVehicles(new HashSet<>(allPersonvehicles));
        Person savedPerson = repo.save(person);

        Set<Integer> allPersonVehicleIds = new HashSet<>();
        for (Vehicle vehicle : savedPerson.getVehicles()) {
            allPersonVehicleIds.add(vehicle.getId());
        }

        return allPersonVehicleIds;
    }

    public Set<Integer> setPersonSpecies(String personId, Set<Integer> personSpecieIds) {
        Person person = repo.findById(Integer.parseInt(personId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Person Not Found", Person.class.getName(), personId);
        });

        List<Specie> allPersonSpecies =
                (List<Specie>) specieRepo.findAllById(personSpecieIds);

        person.setSpecies(new HashSet<>(allPersonSpecies));
        Person savedPerson = repo.save(person);

        Set<Integer> allPersonSpeciesIds = new HashSet<>();
        for (Specie specie : savedPerson.getSpecies()) {
            allPersonSpeciesIds.add(specie.getId());
        }

        return allPersonSpeciesIds;
    }


    public Set<Integer> setPersonStarships(String personId, Set<Integer> personStarshipsIds) {
        Person person = repo.findById(Integer.parseInt(personId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Person Not Found", Person.class.getName(), personId);
        });

        List<Starship> allPersonStarships =
                (List<Starship>) starshipRepo.findAllById(personStarshipsIds);

        person.setStarships(new HashSet<>(allPersonStarships));
        Person savedPerson = repo.save(person);

        Set<Integer> allPersonStarshopsIds = new HashSet<>();
        for (Starship starship : savedPerson.getStarships()) {
            allPersonStarshopsIds.add(starship.getId());
        }

        return allPersonStarshopsIds;
    }
    
}
