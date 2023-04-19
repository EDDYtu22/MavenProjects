package dev.edmond.swapi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.PlanetMapper;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.service.PlanetService;
import dev.edmond.swapi.web.dto.PlanetCreateRequest;
import dev.edmond.swapi.web.dto.PlanetResponse;
import dev.edmond.swapi.web.dto.SwapiPage;

@RestController
@RequestMapping("/swapi/planets")
public class PlanetController {

    @Autowired
    private PlanetMapper planetMapper;

    @Autowired
    private PlanetService planetService;

   

    // @PostMapping(value = "")
    // public ResponseEntity<PlanetResponse> postMethodName(@RequestBody PlanetCreateRequest request) {

    //     Planet planet = planetMapper.planetFromRequest(request);
    //     Planet savedPlanet = planetService.save(planet);
    //     PlanetResponse response = planetMapper.responseFromPlanet(savedPlanet);

    //     return ResponseEntity.ok().body(response);

    // }
    @PostMapping(value = "")
    public List<PlanetResponse> postAllPlanets(@RequestBody List<PlanetCreateRequest> requestList){

        List<Planet> planetList = new ArrayList<>();
        planetList =  planetMapper.planetListFromRequestList(requestList);
        
        List<PlanetResponse> responseList = new ArrayList<>();
        for(Planet planet : planetList){
            Planet savedPlanet = planetService.save(planet);
            PlanetResponse response = planetMapper.responseFromPlanet(savedPlanet);
            responseList.add(response);
        }

        return responseList;
    }


    @GetMapping(name = "", produces = "application/json")
    public SwapiPage<PlanetResponse> getAllFilms( @RequestParam (defaultValue = "1",  required = false) Integer currPage){

        Page<PlanetResponse> planetPage = planetService.fetchAll(currPage - 1, 10).map(planetMapper::responseFromPlanet);

        return new SwapiPage<>(planetPage, "http://localhost:8080/swapi/planets?currPage=" + (currPage + 1), "http://localhost:8080/swapi/planets?currPage=" + (currPage - 1) );

    }
}

