package dev.edmond.swapi.web;

import java.util.List;

import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.edmond.swapi.mapper.PlanetMapper;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.repository.PlanetRepository;
import dev.edmond.swapi.web.dto.PlanetCreateRequest;
import dev.edmond.swapi.web.dto.PlanetResponse;

@RestController
@RequestMapping("/swapi/planets")
public class PlanetController {

    @Autowired
    private PlanetMapper planetMapper;

    @Autowired
    private PlanetRepository planetRepo;

   

    @PostMapping(value = "")
    public ResponseEntity<PlanetResponse> postMethodName(@RequestBody PlanetCreateRequest request) {

        Planet planet = planetMapper.planetFromRequest(request);
        Planet savedPlanet = planetRepo.save(planet);
        PlanetResponse response = planetMapper.responseFromPlanet(savedPlanet);

        return ResponseEntity.ok().body(response);

    }
}
