package com.apirest.crud.Service;

import java.util.List;

import com.apirest.crud.model.user;

public interface userService {

    public  user save(user u);
    public user getUserById(Long id);
    public List<user> getAll();
    public void deleteUser(Long id);
}
