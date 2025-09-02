package com.dev.simpleMvp.demo;

import com.dev.simpleMvp.demo.dto.UserDTO;
import com.dev.simpleMvp.demo.dto.mapper.UserMapper;
import com.dev.simpleMvp.demo.entity.User;
import com.dev.simpleMvp.demo.repository.UserRepository;
import com.dev.simpleMvp.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setNome("Nando");
        user.setEmail("nando@email.com");
        user.setSenha("123456");
        user.setDataNasc(LocalDate.of(2004, 11, 20));

        userDTO = new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getDataNasc(), user.getIdade());
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("Nando", result.getNome());
        assertEquals("nando@email.com", result.getEmail());
        assertEquals(LocalDate.of(2004, 11, 20), result.getDataNasc());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deveBuscarUsuarioPorId() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.map(user)).thenReturn(userDTO);

        UserDTO result = userService.findByIdUsers(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Nando", result.getNome());
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).map(user);
    }

    @Test
    void deveListarTodosUsuarios() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.map(user)).thenReturn(userDTO);

        List<UserDTO> result = userService.findAllUsers();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).map(user);
    }

    @Test
    void deveDeletarUsuarioPorId() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

}
