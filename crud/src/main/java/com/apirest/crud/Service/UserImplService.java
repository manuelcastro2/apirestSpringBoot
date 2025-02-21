package com.apirest.crud.Service;

import java.util.List;
import java.util.Optional;

import com.apirest.crud.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.User;
import com.apirest.crud.repository.userRepository;

@Service
public class UserImplService implements userService {

    @Autowired
    private userRepository userrepository;

    @Override
    public Response<User> save(User u) {
        try {
            User savedUser = userrepository.save(u);
            return new Response<>(savedUser, "user saave correct");
        } catch (Exception e) {
            return new Response<>(null, "Error the save the user: " + e.getMessage());
        }
    }
    
    
    @Override
    public Response<Optional<User>> getUserById(Long id) {
       try{
           Optional<User> obj= userrepository.findById(id);
           return new Response<>(obj,"user found");
       }catch (Exception e){
           return new Response<>(null, "user not found: ");
       }

    }

    @Override
    public Response<List<User>> getAll() {
        try{
            List<User> list= userrepository.findAll();
            return new Response<>(list,"user found");
        }catch (Exception e){
            return new Response<>(null, "search failed: " );
        }
    }

    @Override
    public Response<Boolean> deleteUser(Long id) {
        try{
                userrepository.deleteById(id);
               return new Response<>(true,"user was Deleted");
        } catch (Exception e) {
            return new Response<>(false,"user not deleted");
        }


    }

    @Override
    public Response<User> updateUser(Long id, User user) {
        try{
            User userUpdate=userrepository.save(user);
            return new Response<>(userUpdate,"user found");
        }catch (Exception e){
            return new Response<>(null, "search failed: " + e.getMessage());
        }

    }

}
