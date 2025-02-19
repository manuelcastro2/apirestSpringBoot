package com.apirest.crud.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.crud.Service.userImplService;
import com.apirest.crud.model.User;

@RestController
@RequestMapping("api/users")
public class userController {
    
    @Autowired
    private userImplService userservice;

    @PostMapping("/")
    public User create(@RequestBody User u){
        
        User createdUser =(User) userservice.save(u);
        if(createdUser.getAge()<18) {
            createdUser.setUsername(createdUser.getUsername().toUpperCase());
            createdUser.setLastName(createdUser.getLastName().toUpperCase());
        }
        return createdUser;
    }

    @GetMapping("/")
    public List<Object> getAll(){

        return  userservice.getAll();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id){
        Object u= userservice.getUserById(id);

        return u;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userservice.deleteUser(id);
    }

}
