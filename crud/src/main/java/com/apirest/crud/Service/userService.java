package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import com.apirest.crud.model.User;

public interface userService {

    public User save(User u);
    public Optional<User> getUserById(Long id);
    public List<User> getAll();
    public boolean deleteUser(Long id);
    public User updateUser( Long id,User user);

}
