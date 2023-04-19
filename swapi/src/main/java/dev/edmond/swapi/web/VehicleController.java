package dev.edmond.swapi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.VehicleMapper;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.service.VehicleService;
import dev.edmond.swapi.web.dto.SwapiPage;
import dev.edmond.swapi.web.dto.VehicleDto;

@RestController
@RequestMapping(value = "/swapi/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;

    @PostMapping(value = "")
    public List<VehicleDto> postAllPersons(@RequestBody List<VehicleDto> requestList) {

        List<Vehicle> personList = new ArrayList<>();
        personList = vehicleMapper.vehicleListFromRequestList(requestList);

        List<VehicleDto> responseList = new ArrayList<>();
        for (Vehicle vehicle : personList) {
            Vehicle savedVehicle = vehicleService.save(vehicle);
            VehicleDto response = vehicleMapper.dtoFromVehicle(savedVehicle);
            responseList.add(response);
        }

        return responseList;
    }


    @GetMapping(name = "", produces = "application/json")
    public SwapiPage<VehicleDto> getAllSpecies(@RequestParam(defaultValue = "1", required = false) Integer currPage){
        
        Page<VehicleDto> vehiclePage = vehicleService.fetchAll(currPage - 1, 10).map(vehicleMapper::dtoFromVehicle);

        return new SwapiPage<>(vehiclePage, "http://localhost:8080/swapi/vehicles?currPage=" + (currPage + 1), "http://localhost:8080/swapi/vehicles?currPage=" + (currPage - 1));
    }
}
