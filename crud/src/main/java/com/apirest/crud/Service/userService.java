package com.apirest.crud.Service;

import java.util.List;

import com.apirest.crud.model.User;
import org.springframework.http.ResponseEntity;

public interface userService {

    public User save(User u);
    public User getUserById(Long id);
    public List<User> getAll();
    public void deleteUser(Long id);
}
