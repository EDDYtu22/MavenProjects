package dev.edmond.swapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.edmond.swapi.models.Vehicle;

public interface VehiclePagingRepository extends PagingAndSortingRepository<Vehicle, Integer> {
    
}
