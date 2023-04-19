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

import dev.edmond.swapi.mapper.SpecieMapper;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.service.SpecieService;
import dev.edmond.swapi.web.dto.SpecieDto;
import dev.edmond.swapi.web.dto.SwapiPage;

@RestController
@RequestMapping(value = "/swapi/species")
public class SpecieController {

    @Autowired
    private SpecieService specieService;

    @Autowired
    private SpecieMapper specieMapper;

    @PostMapping(value = "")
    public List<SpecieDto> postAllspecies(@RequestBody List<SpecieDto> requestList) {

        List<Specie> specieList = new ArrayList<>();
        specieList = specieMapper.specieListFromDtoList(requestList);

        List<SpecieDto> responseList = new ArrayList<>();
        for (Specie Specie : specieList) {
            Specie savedspecie = specieService.save(Specie);
            SpecieDto response = specieMapper.dtoFromSpecie(savedspecie);
            responseList.add(response);
        }

        return responseList;
    }



    @GetMapping(name = "", produces = "application/json")
    public SwapiPage<SpecieDto> getAllSpecies(@RequestParam(defaultValue = "1", required = false) Integer currPage){
        
        Page<SpecieDto> speciePage = specieService.fetchAll(currPage - 1, 10).map(specieMapper::dtoFromSpecie);

        return new SwapiPage<>(speciePage, "http://localhost:8080/swapi/species?currPage=" + (currPage + 1), "http://localhost:8080/swapi/species?currPage=" + (currPage - 1));
    }

}
