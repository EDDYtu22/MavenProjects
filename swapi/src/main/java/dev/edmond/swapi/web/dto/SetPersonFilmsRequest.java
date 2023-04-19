package dev.edmond.swapi.web.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SetPersonFilmsRequest {
   
    @NotNull
    Set<Integer> setPersonFilms;
}
