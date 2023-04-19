package dev.edmond.springstart.web.dto;

import java.util.UUID;

import dev.edmond.springstart.models.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {

    private UUID id;

    private String name;

    private Integer age;

    private AddressDto address;

    private String egnNumber;

}