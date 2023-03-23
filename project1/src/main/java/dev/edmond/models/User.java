package dev.edmond.models;

public class User {

    private String username;
    private Integer identifier;
    private String firstName;
    private String lastName;
    
   public User(){}


    public User(String username, Integer identifier, String firstName, String lastName) {
    this.username = username;
    this.identifier = identifier;
    this.firstName = firstName;
    this.lastName = lastName;
}


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getIdentifier() {
        return identifier;
    }
    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        return "[" + username + ", " + identifier + ", " + firstName + ", " + lastName + "]";
    }
    

    
}
