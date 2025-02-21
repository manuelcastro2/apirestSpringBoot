package com.apirest.crud;


import com.apirest.crud.Service.UserImplService;
import com.apirest.crud.controller.userController;
import com.apirest.crud.dtos.UserDto;
import com.apirest.crud.model.Response;
import com.apirest.crud.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CrudApplicationTests {

	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserImplService userservice;
    
    @InjectMocks
    private userController usercontroller;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        User u = new User(1L,"manuel", "castro", "john@example.com",17);

        when(userservice.save(any(User.class))).thenReturn( new Response<>(u, "User created"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Response<User>(u, "User created").getData())))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.username").value("MANUEL"))
                .andExpect(jsonPath("$.data.lastName").value("CASTRO"))
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });

    }


    @Test
    void testListAllUsers() throws Exception {
        User u1 = new User(1L,"manuel", "castro", "john@example.com",17);
        User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);

        List<User> list= List.of(u1,u2);
        when(userservice.getAll()).thenReturn(new Response<>(list,"user list"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("manuel"))
                .andExpect(jsonPath("$.data[0].lastName").value("castro"))
                .andExpect(jsonPath("$.data[1].username").value("carlos"))
                .andExpect(jsonPath("$.data[1].lastName").value("amaya"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void testUserId() throws Exception {
        User u1 = new User(1L,"manuel", "castro", "john@example.com",17);


       when(userservice.getUserById(1L)).thenReturn(new Response<>(Optional.of(u1),"user found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.username").value("manuel"))
                .andExpect(jsonPath("$.data.lastName").value("castro"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {

                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void testDeleteUser() throws Exception{
        User u2 = new User(1L,"carlos", "amaya", "john@example.com",18);

        when(userservice.deleteUser(1L)).thenReturn(new Response<>(true,"user do deleted"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}",u2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(1L)))
                .andExpect(jsonPath("$.data").value(true))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void testUpdateUser() throws Exception{
        User u1 = new User(1L,"carlos", "amaya", "john@example.com",18);
        User u2 = new User(1L,"momo", "momo", "john@example.com",18);

        when(userservice.save(any(User.class))).thenReturn(new Response<>(u1,"user created"));
        when(userservice.updateUser(eq(1L),any(User.class))).thenReturn(new Response<>(u2,"user updated"));


        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("momo"))
                .andExpect(jsonPath("$.data.lastName").value("momo"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });

    }

}



