package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.error.ObjectNotFoundException;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.repository.SpeciePagingRepository;
import dev.edmond.swapi.repository.SpecieRepository;

@Service
public class SpecieService {
    
    @Autowired 
    private SpecieRepository repo;

    @Autowired
    private SpeciePagingRepository pagingRepo;


    public Specie save(Specie specie) {
        return repo.save(specie);
    }

    public Page<Specie> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Specie fetchById(Integer specieId){
        Specie specie = repo.findById(specieId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Specie Not Found", Person.class.getName(), specieId.toString());
        });
        return specie;
    }
}
