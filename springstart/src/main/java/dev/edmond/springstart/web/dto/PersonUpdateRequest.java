package dev.edmond.springstart.web.dto;

import org.hibernate.validator.constraints.Range;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonUpdateRequest {

    private String name;
    
    @Range(min = 0, max = 200)
    private Integer age;

    private AddressDto address;

}
