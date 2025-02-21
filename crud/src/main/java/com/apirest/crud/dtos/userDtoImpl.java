package com.apirest.crud.dtos;

import com.apirest.crud.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class userDtoImpl{

    public UserDto convertToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setLastName(user.getLastName());
        return dto;
    }

}
