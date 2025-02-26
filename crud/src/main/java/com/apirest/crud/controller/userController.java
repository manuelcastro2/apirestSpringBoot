package com.apirest.crud.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.apirest.crud.dtos.UserDto;
import com.apirest.crud.model.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apirest.crud.Service.UserImplService;
import com.apirest.crud.model.User;
import com.validation.Validator;

@RestController
@Api(value = "apiRestFull with connection h2 and structure mvc")
@RequestMapping("api/users")
public class userController {

    @Autowired
    private UserImplService userservice;

    private UserDto convertToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setLastName(user.getLastName());
        return dto;
    }

    @PostMapping("/")
    public Response<UserDto> create(@RequestBody User user) {

        if (equals(user)) return new Response<>(null, "user is empty");

        User createdUser = userservice.save(user).getData();
        UserDto dto = convertToDTO(createdUser);

        Validator validation = new Validator();

        dto.setUsername(validation.ValidatorSpace(dto.getUsername()));
        dto.setLastName(validation.ValidatorSpace(dto.getLastName()));

        String lengthName = validation.ValidatorLength(dto.getUsername());
        if (!Objects.equals(lengthName, dto.getUsername())) return new Response<>(null, lengthName);

        if (createdUser.getAge() < 18) {
            dto.setUsername(validation.ValidatorMayus(dto.getUsername()));
            dto.setLastName(validation.ValidatorMayus(dto.getLastName()));
        }
        return new Response<>(dto, "user created");
    }

    @GetMapping("/")
    public Response<List<UserDto>> getAll() {
        List<User> ListUser = userservice.getAll().getData();
        List<UserDto> userDTOs = ListUser.stream().map(user -> {
            UserDto dto = new UserDto();
            dto.setUsername(user.getUsername());
            dto.setLastName(user.getLastName());
            return dto;
        }).collect(Collectors.toList());

        return new Response<>(userDTOs, "user list");

    }

    @GetMapping("/{id}")
    public Response<UserDto> getById(@PathVariable("id") Long id) {

        if (equals(id.toString())) return new Response<>(null, "foul the id the user");

        User u = userservice.getUserById(id).getData();
        UserDto dto = convertToDTO(u);
        return new Response<>(dto, "user found");
    }

    @PutMapping("/{id}")
    public Response<UserDto> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        if (equals(id.toString())) return new Response<>(null, "id is required");
        if (equals(user)) return new Response<>(null, "data the user is required");
        User update = userservice.updateUser(id, user).getData();
        UserDto dto = convertToDTO(update);
        return new Response<>(dto, "user updated");
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteUserById(@PathVariable("id") Long id) {
        if (equals(id.toString())) return new Response<>(null, "id is required");
        Boolean user = userservice.deleteUser(id).getData();
        return new Response<>(user, "user do deleted");
    }


}
