package com.apirest.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apirest.crud.Service.userImplService;
import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@ExtendWith(MockitoExtension.class)
class TestService {
	
	@InjectMocks
	private userImplService userservice;
	
	@Mock	
	private userRepository userrepository;
	
	
	@Test
	void TestSgetById() {
		
		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		
		when(userrepository.findById(1L)).thenReturn(Optional.of(u1));
		
		User response=(User)userservice.getUserById(1L);
		System.out.println(response);
		assertNotNull(response, "the object not null");
		 assertEquals("mario", response.getUsername());
		 assertEquals("mario.bros@email.com", response.getEmail());
	}

	@Test
	void testAllUsers() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);
		when(userrepository.findAll()).thenReturn(List.of(u1,u2));

		List response= userservice.getAll();

		System.out.println(response);

	}

	@Test
	void TestCreate() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);

		when(userrepository.save(u1)).thenReturn(u1);

		User response=(User)userservice.save(u1);

		assertNotNull(response, "the object not null");
		assertEquals("mario", response.getUsername());
		assertEquals("mario.bros@email.com", response.getEmail());
		System.out.println(response);
	}


}
