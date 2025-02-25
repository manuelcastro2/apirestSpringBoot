package com.apirest.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.apirest.crud.model.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apirest.crud.Service.UserImplService;
import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@ExtendWith(MockitoExtension.class)
class TestService {
	
	@InjectMocks
	private UserImplService userservice;
	
	@Mock	
	private userRepository userrepository;
	
	
	@Test
	void TestSgetById() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		
		when(userrepository.findById(any(Long.class))).thenReturn(Optional.of(u1));
		
		Response<Optional<User>> response=userservice.getUserById(1L);


		 assertEquals("mario", response.getData().get().getUsername());
		 assertEquals("mario.bros@email.com", response.getData().get().getEmail());
	}

	@Test
	void testAllUsers() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		User u2 = new User(2L,"carlos", "amaya", "john@example.com",18);
		List list=List.of(u1,u2);
		when(userrepository.findAll()).thenReturn(list);

		Response<List<User>> response= userservice.getAll();

		assertEquals(List.of(u1,u2), response.getData());
		for (int i=0 ;i<response.getData().size();i++) {
			assertEquals( list.get(i),response.getData().get(i));
		}
	}

	@Test
	void TestCreate() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);

		when(userrepository.save(any(User.class))).thenReturn(u1);

		Response<User> response=userservice.save(u1);

		assertEquals("mario", response.getData().getUsername());
		assertEquals("mario.bros@email.com", response.getData().getEmail());
	}

	@Test
	void testDelete() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		doNothing().when(userrepository).deleteById(1L);
		Response<Boolean> test=userservice.deleteUser(1L);

		assertEquals(true, test.getData());
		verify(userrepository, times(1)).deleteById(1L);
	}

	@Test
	void testUpdate() {

		User u1=new User(1L, "mario", "castro", "mario.bros@email.com", 18);
		User u2=new User(1L, "javier", "rodriguez", "javier.bros@email.com", 18);

		when(userrepository.save(any(User.class))).thenReturn(u2);
		Response<User> result = userservice.updateUser(1L,u2);
		assertEquals("javier", result.getData().getUsername());
		assertEquals("rodriguez", result.getData().getLastName());
	}
}
