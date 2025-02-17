package com.apirest.crud.controller;

import java.util.List;

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
import com.apirest.crud.model.user;

@RestController
@RequestMapping("api/users")
public class userController {
    
    @Autowired
    private userImplService userservice;

    @PostMapping("/")
    public user create(@RequestBody user u){
        if(u.getAge()<18) {
            u.setUsername(u.getUsername().toUpperCase());
            u.setLastName(u.getLastName().toUpperCase());
        }
        user createdUser = userservice.save(u);
        return createdUser;
    }

    @GetMapping("/")
    public List<user> getAll(){
        return userservice.getAll();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id){
        user u= userservice.getUserById(id);
    if(u==null) return "usuario no encontrado";

        return u;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userservice.deleteUser(id);
    }

}
