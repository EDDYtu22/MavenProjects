package dev.edmond.swapi.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;

import dev.edmond.swapi.models.Film;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.models.Planet;
import dev.edmond.swapi.models.Specie;
import dev.edmond.swapi.models.Starship;
import dev.edmond.swapi.models.Vehicle;
import dev.edmond.swapi.web.dto.FilmCreateRequest;
import dev.edmond.swapi.web.dto.FilmResponse;

@Mapper
public interface FilmMapper {
    
    public Film filmFromDto(FilmCreateRequest dto);
    
    
    public FilmResponse responseFromFilm(Film film);

    public static Set<String> personUrlsFromFilm(Set<Person> persons){
        Set<String> personUrls = new HashSet<>();

        if(persons != null){
            for(Person person : persons){
                personUrls.add("http://localhost:8080/swapi/persons/" + person.getId());
            }
        }

        return personUrls;
    }

    public static Set<String> vehicleUrlsFromFilm(Set<Vehicle> vehicles){
        Set<String> vehicleUrls = new HashSet<>();

       if(vehicles != null){
        for(Vehicle vehicle : vehicles){
            vehicleUrls.add("http://localhost:8080/swapi/vehicles/" + vehicle.getId());
        }
       }

        return vehicleUrls;
    }

    public static Set<String> starshipUrlsFromFilm(Set<Starship> starships){
        Set<String> starshipUrls = new HashSet<>();

        if(starships!=null){
            for(Starship starship : starships){
                starshipUrls.add("http://localhost:8080/swapi/starships/" + starship.getId());
            }
        }

        return starshipUrls;
    }

    public static Set<String> specieUrlsFromFilm(Set<Specie> species){
        Set<String> specieUrls = new HashSet<>();

        if(species != null){
            for(Specie specie : species){
                specieUrls.add("http://localhost:8080/swapi/species/" + specie.getId());
            }
        }

        return specieUrls;
    }

    public static Set<String> planetUrlsFromFilm(Set<Planet> planets){
        Set<String> planetUrls = new HashSet<>();

       if (planets != null){
        for(Planet planet : planets){
            planetUrls.add("http://localhost:8080/swapi/planets/" + planet.getId());
        }
       }

        return planetUrls;
    }


}
