package dev.edmond.springstart.mapper;

import org.mapstruct.Mapper;

import dev.edmond.springstart.models.Address;
import dev.edmond.springstart.web.dto.AddressDto;

@Mapper
public interface AddressMapper {

    //AddressDto -> Address
    public AddressDto modelToDto(Address address);

    //Address -> AddressDto
    public Address dtoToModel(AddressDto addressDto);
    
}
