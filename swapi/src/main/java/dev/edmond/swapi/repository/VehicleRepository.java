package dev.edmond.swapi.repository;

import org.springframework.data.repository.CrudRepository;

import dev.edmond.swapi.models.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    
}
