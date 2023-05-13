package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.error.ObjectNotFoundException;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.repository.StarshipPagingRepository;
import dev.edmond.swapi.repository.StarshipRepository;

@Service
public class StarshipService {
    

    @Autowired 
    private StarshipRepository repo;

    @Autowired
    private StarshipPagingRepository pagingRepo;


    public Starship save(Starship starship) {
        return repo.save(starship);
    }

    public Page<Starship> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Starship fetchById(Integer starshipId){
        Starship starship = repo.findById(starshipId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Starship Not Found", Starship.class.getName(), starshipId.toString());
        });
        return starship;
    }
}
