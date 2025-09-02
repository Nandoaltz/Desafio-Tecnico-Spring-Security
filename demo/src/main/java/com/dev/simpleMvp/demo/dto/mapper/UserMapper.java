package com.dev.simpleMvp.demo.dto.mapper;

import com.dev.simpleMvp.demo.dto.UserDTO;
import com.dev.simpleMvp.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getDataNasc(), user.getIdade());
        return userDTO;
    }
    public User map(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        user.setDataNasc(userDTO.getDataNasc());
        user.setIdade(userDTO.getIdade());
    return user;
    }
}
