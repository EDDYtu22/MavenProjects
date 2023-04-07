package dev.edmond.springstart.web;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.edmond.springstart.error.InvalidObjectException;
import dev.edmond.springstart.error.NotFoundObjectException;
import lombok.Data;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
 
    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<GenericExceptionBody> handleInvalidObjectException(InvalidObjectException ex){

        GenericExceptionBody exceptionBody = new GenericExceptionBody(ex.getErrorId(), ex.getMessage(), ex.getErrors(), null);

        return  ResponseEntity.badRequest().body(exceptionBody);
    }

    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<GenericExceptionBody> handleNotFoundObjectexception(NotFoundObjectException ex){

        GenericExceptionBody exceptionBody = new GenericExceptionBody(ex.getErrorId(), ex.getMessage(), null, ex.getObjectClazz());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionBody);
    }

    @Data
    private static class GenericExceptionBody{
        private final UUID id;
        private final String message;
        private final String clazz;
        private final Map<String, String> errors;

        public GenericExceptionBody(UUID id, String message, Map<String, String> errors, String clazz){
            this.id = id;
            this.errors = errors;
            this.clazz = clazz;
            this.message = message;
        }

        

        
    }

}
