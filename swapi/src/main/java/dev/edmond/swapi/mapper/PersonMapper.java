package dev.edmond.swapi.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.web.dto.PersonCreateRequest;
import dev.edmond.swapi.web.dto.PersonResponse;

@Mapper
public interface PersonMapper {
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "films", ignore = true)
    @Mapping(target = "species", ignore = true)
    @Mapping(target = "starships", ignore = true)
    //@Mapping(target = "planet", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "vehicles", ignore = true)
    public Person personFromRequest(PersonCreateRequest request);

    public PersonCreateRequest requestFromPerson(Person person);


    
    public  PersonResponse responseFromPerson(Person person);

    public static String planetToString(Planet planet){
        if(planet == null) return "unknown";
        return "http://localhost:8080/swapi/planets/" + planet.getId();
    }

    public static Set<String> filmUrlsFromPerson(Set<Film> films){
        Set<String> filmUrls = new HashSet<>();

        for(Film film : films){
            filmUrls.add("http://localhost:8080/swapi/films/" + film.getId());
        }

        return filmUrls;
    }

    public static Set<String> vehicleUrlsFromPerson(Set<Vehicle> vehicles){
        Set<String> vehicleUrls = new HashSet<>();

        for(Vehicle vehicle : vehicles){
            vehicleUrls.add("http://localhost:8080/swapi/vehicles/" + vehicle.getId());
        }

        return vehicleUrls;

    }
    public static Set<String> specieUrlsFromPerson(Set<Specie> species){
        Set<String> specieUrls = new HashSet<>();

        for(Specie specie : species){
            specieUrls.add("http://localhost:8080/swapi/species/" + specie.getId());
        }

        return specieUrls;

    }

    public static Set<String> starshipUrlsFromPerson(Set<Starship> starships){
        Set<String> starshipUrls = new HashSet<>();

        for(Starship starship : starships){
            starshipUrls.add("http://localhost:8080/swapi/starships/" + starship.getId());
        }

        return starshipUrls;

    }




    public List<Person> personListFromRequestList(List<PersonCreateRequest> requestList);

}
