package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import com.apirest.crud.model.Response;
import com.apirest.crud.model.User;

public interface UserService {

    public Response<User> save(User u);
    public Response<User> getUserById(Long id);
    public Response<List<User>> getAll();
    public Response<Boolean> deleteUser(Long id);
    public Response<User> updateUser( Long id,User user);

}
