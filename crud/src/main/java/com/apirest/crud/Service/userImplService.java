package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import com.apirest.crud.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@Service
public class userImplService implements userService {

    @Autowired
    private userRepository userrepository;

    @Override
    public Object save(User u) {
        Object obj = userrepository.save(u);
        if (obj == null) {
            return new Response(null, "user not found");
        }
        return obj;
    }
    
    
    @Override
    public Object getUserById(Long id) {
       Object obj= userrepository.findById(id).orElse(null);
        if (obj == null) {
            return new Response(null, "user not exists");
        }
        return obj;
    }

    @Override
    public List<Object> getAll() {
        List obj=userrepository.findAll();
        if (obj == null) {
            return (List<Object>) new Response(null, "user not exists");
        }
        return  obj;
    }

    @Override
    public void deleteUser(Long id) {
         userrepository.deleteById(id);
    }
    
}
