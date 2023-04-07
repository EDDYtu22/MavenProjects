package dev.edmond.springstart.web.dto;

import org.hibernate.validator.constraints.Range;

import dev.edmond.springstart.models.Address;
import dev.edmond.springstart.validation.ValidEgn;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCreateRequest {

    private String name;
    @Range(min = 0, max = 200)
    private Integer age;

    private AddressDto address;

    @ValidEgn
    private String egnNumber;

    

}
