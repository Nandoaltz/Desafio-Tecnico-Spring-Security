package com.dev.simpleMvp.demo.services;

import com.dev.simpleMvp.demo.dto.UserDTO;
import com.dev.simpleMvp.demo.entity.User;
import com.dev.simpleMvp.demo.dto.mapper.UserMapper;
import com.dev.simpleMvp.demo.repository.UserRepository;
import com.dev.simpleMvp.demo.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User saveUser(User user){
        UserUtils u = new UserUtils();
        int idade = u.calculaIdade(user.getDataNasc());
        user.setIdade(idade);
        return userRepository.save(user);
    }

    public List<UserDTO> findAllUsers(){
        List<User> u = userRepository.findAll();
        return u.stream().map(userMapper::map).collect(Collectors.toList());
    }

    public UserDTO findByIdUsers(Long Id){
        User u = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Usuario inexixtente"));
        return userMapper.map(u);
    }

    public UserDTO updateUser(Long id, UserDTO u){
      User usuarioEsistente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar usuario"));

        usuarioEsistente.setEmail(u.getEmail());
        usuarioEsistente.setNome(u.getNome());

        User userUpdate = userRepository.save(usuarioEsistente);
        return userMapper.map(userUpdate);
    }

    public void deleteUser(Long id){
         userRepository.deleteById(id);
    }
}
