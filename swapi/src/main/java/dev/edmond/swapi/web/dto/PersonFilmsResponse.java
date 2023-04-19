package dev.edmond.swapi.web.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonFilmsResponse {
    private Set<Integer> personFilmIds;
}
