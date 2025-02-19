package com.apirest.crud.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.apirest.crud.model.Response;
import com.apirest.crud.model.User;
import org.springframework.http.ResponseEntity;

public interface userService {

    public Object save(User u);
    public Object getUserById(Long id);
    public List<Object> getAll();
    public void deleteUser(Long id);
}
