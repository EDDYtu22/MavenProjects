package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.error.ObjectNotFoundException;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.repository.PlanetPagingRepository;
import dev.edmond.swapi.repository.PlanetRepository;

@Service
public class PlanetService {
    
    @Autowired 
    private PlanetRepository repo;

    @Autowired
    private PlanetPagingRepository pagingRepo;


    public Planet save(Planet planet) {
        return repo.save(planet);
    }

    public Page<Planet> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Planet fetchById(Integer planetId){
        Planet planet = repo.findById(planetId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Planet Not Found", Planet.class.getName(), planetId.toString());
        });
        return planet;
    }
}
