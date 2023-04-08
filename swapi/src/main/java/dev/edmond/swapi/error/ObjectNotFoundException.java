package dev.edmond.swapi.error;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends SwapiBaseException{
    
    private final String objectClass;
    private final String id;

    public ObjectNotFoundException(String message, String objectClass, String id){
        super(message);
        this.objectClass = objectClass;
        this.id = id;
    }
    


}
