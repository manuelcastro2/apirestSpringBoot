package com.apirest.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
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
		
		Optional<User> response=userservice.getUserById(1L);
		System.out.println(response);
		assertNotNull(response, "the object not null");
		 assertEquals("mario", response.get().getUsername());
		 assertEquals("mario.bros@email.com", response.get().getEmail());
	}

	@Test
	void testAllUsers() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);
		List list=List.of(u1,u2);
		when(userrepository.findAll()).thenReturn(list);

		List response= userservice.getAll();

		assertNotNull(response.size(), "the object not null");
		assertEquals(List.of(u1,u2), response);
		for (int i=0 ;i<response.size();i++) {
			assertEquals( list.get(i),response.get(i));
		}
	}

	@Test
	void TestCreate() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);

		when(userrepository.save(u1)).thenReturn(u1);

		User response=userservice.save(u1);

		assertNotNull(response, "the object not null");
		assertEquals("mario", response.getUsername());
		assertEquals("mario.bros@email.com", response.getEmail());
	}

	@Test
	void testDelete() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);

		when(userrepository.existsById(u1.getId())).thenReturn(true);

		boolean test=userservice.deleteUser(1L);
		System.out.println(test);
		verify(userservice,times(1)).deleteUser(1L);
	}

	@Test
	void testUpdate() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		User u2=new User(1L, "javier", "rodriguez", "javier.bros@email.com", 18);

		when(userrepository.existsById(u1.getId())).thenReturn(true);
		when(userrepository.save(any(User.class))).thenReturn(u2);
		User result = userservice.updateUser(1L,u2);
		assertEquals(u2.toString(), result.toString());
	}
}
