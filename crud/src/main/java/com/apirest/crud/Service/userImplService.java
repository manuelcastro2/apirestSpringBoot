package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.user;
import com.apirest.crud.repository.userRepository;

@Service
public class userImplService implements userService {

    @Autowired
    private userRepository userrepository;

    @Override
    public user save(user u) {

        return userrepository.save(u);
    }
    @Override
    public user getUserById(Long id) {
       Optional<user> optionalUser= userrepository.findById(id);
       return optionalUser.get();
    }

    @Override
    public List<user> getAll() {
     return userrepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
         userrepository.deleteById(id);
    }
    
}
