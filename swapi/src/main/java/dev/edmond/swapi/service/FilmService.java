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
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.repository.FilmPagingRepository;
import dev.edmond.swapi.repository.FilmRepository;
import dev.edmond.swapi.repository.PlanetRepository;
import dev.edmond.swapi.repository.SpecieRepository;
import dev.edmond.swapi.repository.StarshipRepository;
import dev.edmond.swapi.repository.VehicleRepository;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repo;

    @Autowired
    private FilmPagingRepository pagingRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private SpecieRepository specieRepo;

    @Autowired
    private StarshipRepository starshipRepo;

    @Autowired
    private PlanetRepository planetRepo;

    public Film save(Film film) {
        return repo.save(film);
    }

    public Page<Film> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Film fetchById(Integer filmId){
        Film film = repo.findById(filmId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Film Not Found", Person.class.getName(), filmId.toString());
        });
        return film;
    }

    public Set<Integer> setFilmVehicles(String filmId, Set<Integer> vehicleIds) {

        Film film = repo.findById(Integer.parseInt(filmId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Film Not Found", Person.class.getName(), filmId);
        });

        List<Vehicle> allFilmVehicles = (List<Vehicle>) vehicleRepo.findAllById(vehicleIds);

        film.setVehicles(new HashSet<>(allFilmVehicles));
        Film savedFilm = repo.save(film);

        Set<Integer> allFilmVehicleIds = new HashSet<>();
        for (Vehicle vehicle : savedFilm.getVehicles()) {
            allFilmVehicleIds.add(vehicle.getId());
        }

        return allFilmVehicleIds;
    }

    public Set<Integer> setFilmStarships(String filmId, Set<Integer> starshipIds) {

        Film film = repo.findById(Integer.parseInt(filmId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Film Not Found", Person.class.getName(), filmId);
        });

        List<Starship> allFilmStarships = (List<Starship>) starshipRepo.findAllById(starshipIds);

        film.setStarships(new HashSet<>(allFilmStarships));
        Film savedFilm = repo.save(film);

        Set<Integer> allFilmStarshipIds = new HashSet<>();
        for (Starship starship : savedFilm.getStarships()) {
            allFilmStarshipIds.add(starship.getId());
        }

        return allFilmStarshipIds;
    }

    public Set<Integer> setFilmSpecies(String filmId, Set<Integer> specieIds) {

        Film film = repo.findById(Integer.parseInt(filmId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Film Not Found", Person.class.getName(), filmId);
        });

        List<Specie> allFilmSpecies = (List<Specie>) specieRepo.findAllById(specieIds);

        film.setSpecies(new HashSet<>(allFilmSpecies));
        Film savedFilm = repo.save(film);

        Set<Integer> allFilmSpecieIds = new HashSet<>();
        for (Specie specie : savedFilm.getSpecies()) {
            allFilmSpecieIds.add(specie.getId());
        }

        return allFilmSpecieIds;
    }

    public Set<Integer> setFilmPlanets(String filmId, Set<Integer> planetIds) {

        Film film = repo.findById(Integer.parseInt(filmId)).orElseThrow(() -> {
            throw new ObjectNotFoundException("Film Not Found", Person.class.getName(), filmId);
        });

        List<Planet> allFilmPlanets = (List<Planet>) planetRepo.findAllById(planetIds);

        film.setPlanets(new HashSet<>(allFilmPlanets));
        Film savedFilm = repo.save(film);

        Set<Integer> allFilmPlanetIds = new HashSet<>();
        for (Planet planet : savedFilm.getPlanets()) {
            allFilmPlanetIds.add(planet.getId());
        }

        return allFilmPlanetIds;
    }
}
