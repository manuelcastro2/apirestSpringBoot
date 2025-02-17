package com.apirest.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;    
    private String username;    
    private String lastName;
    private String email;
    private int years;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return years;
    }
    public void setAge(int years) {
        this.years = years;
    }

    public user(Long id, String username, String lastName, String email, int years) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.years = years;
    }
}
