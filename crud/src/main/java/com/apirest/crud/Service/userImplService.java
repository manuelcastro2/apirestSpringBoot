package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@Service
public class userImplService implements userService {

    @Autowired
    private userRepository userrepository;

    @Override
    public User save(User u) {
        return userrepository.save(u);
    }
    
    
    @Override
    public User getUserById(Long id) {
       Optional<User> u= userrepository.findById(id);
       return u.get();
    }

    @Override
    public List<User> getAll() {
     return userrepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {

        userrepository.deleteById(id);
    }
    
}
