package com.apirest.crud;


import com.apirest.crud.Service.userImplService;
import com.apirest.crud.controller.userController;
import com.apirest.crud.model.user;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(userController.class)
@ExtendWith(MockitoExtension.class)
class CrudApplicationTests {

	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userImplService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        user u = new user(1L,"manuel", "castro", "john@example.com",17);
        if(u.getAge()<18) {
            u.setUsername(u.getUsername().toUpperCase());
            u.setLastName(u.getLastName().toUpperCase());
        }
        when(userService.save(Mockito.any(user.class))).thenReturn(u);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }


    @Test
    void testListAllUsers() throws Exception {
        user u1 = new user(1L,"manuel", "castro", "john@example.com",17);
        user u2 = new user(2L,"carlos", "amaya", "john@example.com",18);

        when(userService.getAll()).thenReturn(List.of(u1,u2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {

                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void testUserId() throws Exception {
        user u1 = new user(1L,"manuel", "castro", "john@example.com",17);
        user u2 = new user(2L,"carlos", "amaya", "john@example.com",18);


       when(userService.getUserById(1L)).thenReturn(u1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("manuel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {

                    System.out.println(result.getResponse().getContentAsString());
                });
    }

}



