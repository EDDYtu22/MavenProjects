package dev.edmond.swapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.web.dto.VehicleDto;

@Mapper
public interface VehicleMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "pilots", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public Vehicle vehicleFromDto (VehicleDto dto);

    public VehicleDto dtoFromVehicle (Vehicle vehicle);

    public List<Vehicle> vehicleListFromRequestList(List<VehicleDto> requestList);

}
