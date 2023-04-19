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

import dev.edmond.swapi.mapper.StarshipMapper;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.service.StarshipService;
import dev.edmond.swapi.web.dto.StarshipDto;
import dev.edmond.swapi.web.dto.SwapiPage;

@RestController
@RequestMapping(value = "/swapi/starships")
public class StarshipController {

    @Autowired
    private StarshipService starshipService;

    @Autowired
    private StarshipMapper starshipMapper;

    @PostMapping(value = "")
    public List<StarshipDto> postAllStarships(@RequestBody List<StarshipDto> requestList) {

        List<Starship> starshipList = new ArrayList<>();
        starshipList = starshipMapper.starshipListFromRequestList(requestList);

        List<StarshipDto> responseList = new ArrayList<>();
        for (Starship Starship : starshipList) {
            Starship savedStarship = starshipService.save(Starship);
            StarshipDto response = starshipMapper.dtoFromStarship(savedStarship);
            responseList.add(response);
        }

        return responseList;
    }


    @GetMapping(name = "", produces = "application/json")
    public SwapiPage<StarshipDto> getAllSpecies(@RequestParam(defaultValue = "1", required = false) Integer currPage){
        
        Page<StarshipDto> starshipPage = starshipService.fetchAll(currPage - 1, 10).map(starshipMapper::dtoFromStarship);

        return new SwapiPage<>(starshipPage, "http://localhost:8080/swapi/starships?currPage=" + (currPage + 1), "http://localhost:8080/swapi/starships?currPage=" + (currPage - 1));
    }
}
