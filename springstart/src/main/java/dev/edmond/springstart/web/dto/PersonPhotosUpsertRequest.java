package dev.edmond.springstart.web.dto;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonPhotosUpsertRequest {

    @NotNull
    private Set<UUID> personPhotoIds;


    
}




//pojo - plain old java object 