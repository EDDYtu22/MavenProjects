package dev.edmond.swapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.repository.VehiclePagingRepository;
import dev.edmond.swapi.repository.VehicleRepository;

@Service
public class VehicleService {
    

    @Autowired 
    private VehicleRepository repo;

    @Autowired
    private VehiclePagingRepository pagingRepo;


    public Vehicle save(Vehicle vehicle) {
        return repo.save(vehicle);
    }

    public Page<Vehicle> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }
}
