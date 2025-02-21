package com.apirest.crud.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.apirest.crud.dtos.UserDto;
import com.apirest.crud.dtos.userDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apirest.crud.Service.userImplService;
import com.apirest.crud.model.User;

@RestController
@RequestMapping("api/users")
public class userController {
    
    @Autowired
    private userImplService userservice;

    @Autowired
    private userDtoImpl userDtoImpl;

    @PostMapping("/")
    public UserDto create(@RequestBody User u){
        
        User createdUser = userservice.save(u);
        UserDto dto= userDtoImpl.convertToDTO(createdUser);
        if(createdUser.getAge()<18) {
            dto.setUsername(dto.getUsername().toUpperCase());
            dto.setLastName(dto.getLastName().toUpperCase());
        }
        return dto;
    }

    @GetMapping("/")
    public List<UserDto> getAll(){
        List<User> ListUser=userservice.getAll();
        List<UserDto> userDTOs = ListUser.stream()
                .map(user->{
                    UserDto dto= new UserDto();
                    dto.setUsername(user.getUsername());
                    dto.setLastName(user.getLastName());
                    return dto;
                }).collect(Collectors.toList());

        return userDTOs;

    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id){
       Optional<User> u= userservice.getUserById(id);
        UserDto dto= userDtoImpl.convertToDTO(u.get());

        return dto;
    }

    @PutMapping("/{id}")
    public UserDto updateUserById(@PathVariable("id")Long id,@RequestBody User user){
            User update= userservice.updateUser(id,user);
            UserDto dto = userDtoImpl.convertToDTO(update);
            return dto;

    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable("id") Long id){
        if(userservice.deleteUser(id)){
            return true;
        }
        return false;
    }



}
