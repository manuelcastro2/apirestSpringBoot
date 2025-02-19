package com.apirest.crud;


import com.apirest.crud.Service.userImplService;
import com.apirest.crud.controller.userController;
import com.apirest.crud.model.User;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CrudApplicationTests {

	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userImplService userservice;
    
    @InjectMocks
    private userController usercontroller;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        User u = new User(1L,"manuel", "castro", "john@example.com",17);

        when(userservice.save(Mockito.any(User.class))).thenReturn(u);

        User userC=usercontroller.create(u);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userC)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });

    }


    @Test
    void testListAllUsers() throws Exception {
        User u1 = new User(1L,"manuel", "castro", "john@example.com",17);
        User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);

        List<Object> list= List.of(u1,u2);
        when(userservice.getAll()).thenReturn(list);

        List userC=  usercontroller.getAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userC)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {

                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void testUserId() throws Exception {
        User u1 = new User(1L,"manuel", "castro", "john@example.com",17);
        User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);

       when(userservice.getUserById(1L)).thenReturn(u1);

        User userC= (User) usercontroller.getById(u2.getId());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",u2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userC)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {

                    System.out.println(result.getResponse().getContentAsString());
                });
    }

}



