package dev.edmond.springstart.web.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
public class PersonPhotosResponse {

    private Set<UUID> personPhotoIds;

}

// pojo - plain old java object