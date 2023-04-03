package dev.edmond.springstart.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Person {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    private String name;
    @Range(min = 0, max = 200)
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    @ManyToMany
    @JoinTable (
        name = "person_photo",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    @JsonIgnore
    private Set<Photo> photos;
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public UUID getId() {
        return id;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Set<Photo> getPhotos() {
        return photos;
    }
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
    public String toString(){
        return "ID: " + id + "\nName" + name + "\nAge: " + age;
    }

    public List<String> patchAtributes(Person person2){
        List<String> edits = new ArrayList<>();
        if(this.getAddress() != person2.getAddress() && person2.getAddress() != null){
            this.setAddress(person2.getAddress());
            edits.add("Address updated to: " + this.getAddress());
        }
        if(this.getAge() != person2.getAge() && person2.getAge() > 0){
            this.setAge(person2.getAge());
            edits.add("Age updated to: " + this.getAge());
        }
        if(this.getName() != person2.getName() && person2.getName() != null){
            this.setName(person2.getName());
            edits.add("Name updated to: " + this.getName());
        }

        return edits;
    }

    

}
