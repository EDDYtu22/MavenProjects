package dev.edmond.springstart.web.dto;

import dev.edmond.springstart.models.Person;

public class PersonResponse{

    private Person person;

    public Person getPerson(){
        return this.person;
    }

    public void setPerson(Person person){
        this.person = person;
    }
    

    




}