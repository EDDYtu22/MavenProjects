package dev.edmond.springstart.web.dto;

import java.util.Set;
import java.util.UUID;

public class PersonPhotosResponse {

    private Set<UUID> personPhotoIds;

    public Set<UUID> getPersonPhotoIds() {
        return personPhotoIds;
    }

    public void setPersonPhotoIds(Set<UUID> personPhotoIds) {
        this.personPhotoIds = personPhotoIds;
    }

    
}




//pojo - plain old java object 