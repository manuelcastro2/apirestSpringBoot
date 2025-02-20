package com.apirest.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apirest.crud.Service.userImplService;
import com.apirest.crud.model.User;

@RestController
@RequestMapping("api/users")
public class userController {
    
    @Autowired
    private userImplService userservice;

    @PostMapping("/")
    public User create(@RequestBody User u){
        
        User createdUser = userservice.save(u);
        if(createdUser.getAge()<18) {
            createdUser.setUsername(createdUser.getUsername().toUpperCase());
            createdUser.setLastName(createdUser.getLastName().toUpperCase());
        }
        return createdUser;
    }

    @GetMapping("/")
    public List<User> getAll(){
        return  userservice.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable("id") Long id){
        Optional<User> u= userservice.getUserById(id);

        return u;
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id")Long id,@RequestBody User user){
            User update= userservice.updateUser(id,user);
            return update;

    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable("id") Long id){
        if(userservice.deleteUser(id)){
            return true;
        }
        return false;
    }



}
