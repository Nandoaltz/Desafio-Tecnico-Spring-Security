package com.dev.simpleMvp.demo;

import com.dev.simpleMvp.demo.entity.User;
import com.dev.simpleMvp.demo.repository.UserRepository;
import com.dev.simpleMvp.demo.security.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService service;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("nando@email.com");
        user.setSenha("123456");
    }

    @Test
    void loadUserByUsernameDeveRetornarUserDetailsQuandoUsuarioExiste() throws Exception {
        // Arrange
        when(userRepository.findFirstByEmail("nando@email.com")).thenReturn(Optional.of(user));

        // Act
        UserDetails result = service.loadUserByUsername("nando@email.com");

        // Assert
        assertNotNull(result);
        assertEquals("nando@email.com", result.getUsername());
        assertEquals("123456", result.getPassword());
        verify(userRepository, times(1)).findFirstByEmail("nando@email.com");
    }
}
