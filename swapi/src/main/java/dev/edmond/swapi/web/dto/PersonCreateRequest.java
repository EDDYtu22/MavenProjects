package dev.edmond.swapi.web.dto;

import dev.edmond.swapi.validation.ValidGender;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PersonCreateRequest {
    

    @Id
    private Integer id;

    private String name;

    private String height;

    private String mass;

    private String hair_color;

    private String eye_color;

    private String skin_color;

    private String birth_year;

    @ValidGender
    private String gender;

}
