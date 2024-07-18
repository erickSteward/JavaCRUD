package org.example;

public class User {
    private int id;
    private String name;
    private String email;

    public User(String name, String email){
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email) {
        this.id = id;
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
    }

    //Getters and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }
    //Validation Methods
    private void validateName(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name canot be null or empty");
        }
    }

    private void validateEmail(String email){
        if (email == null || email.isEmpty() || !email.contains("@")){
            throw new IllegalArgumentException("Invalid Email address");
        }
    }

}
