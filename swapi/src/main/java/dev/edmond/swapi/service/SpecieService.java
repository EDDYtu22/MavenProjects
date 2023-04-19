package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
}
