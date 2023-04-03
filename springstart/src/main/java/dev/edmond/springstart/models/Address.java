package dev.edmond.springstart.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private UUID id;
    
    private String street;
    private int number;

    @OneToOne(mappedBy = "address")
    private Person person;
    
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        return this.street + " #" + number;
    }
    
}
