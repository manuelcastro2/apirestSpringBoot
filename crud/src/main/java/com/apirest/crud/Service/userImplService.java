package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@Service
public class userImplService implements userService {

    @Autowired
    private userRepository userrepository;

    @Override
    public User save(User u) {
        User obj = userrepository.save(u);
        if (obj == null) {
            throw new NullPointerException("user not found");
        }
        return obj;
    }
    
    
    @Override
    public Optional<User> getUserById(Long id) {
       Optional<User> obj= userrepository.findById(id);
        if (obj == null) {
             throw new NullPointerException("user not exists");
        }
        return obj;
    }

    @Override
    public List<User> getAll() {
        List obj=userrepository.findAll();
        if (obj == null) {
             throw new NullPointerException("users not exists");
        }
        return  obj;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            if(userrepository.existsById(id)){
                userrepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new NullPointerException("the object witch ID " + id + " not exists");
        }


    }

    @Override
    public User updateUser(Long id,User user) {

            if(!userrepository.existsById(id)) {
              throw new NullPointerException  ("the user not exists");
            }
            User userUpdate=userrepository.save(user);
            return userUpdate;
    }

}
