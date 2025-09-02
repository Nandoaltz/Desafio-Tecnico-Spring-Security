package com.dev.simpleMvp.demo.security.controller;

import com.dev.simpleMvp.demo.entity.LoginRequest;
import com.dev.simpleMvp.demo.entity.User;
import com.dev.simpleMvp.demo.repository.UserRepository;
import com.dev.simpleMvp.demo.security.service.ServiceToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Controlador responsavel pela autenticação")
public class AuthController {
    private final UserRepository userRepository;
    private final ServiceToken tokenService;

    public AuthController(UserRepository userRepository, ServiceToken tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "Rota para autenticar Usuário",
            description = "Faz a autenticação do Usuário passando os campos email e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Autenticação bem sucedida"),
            @ApiResponse(responseCode = "403", description = "Não autorizado")
    })
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        try {
            User user = userRepository.findFirstByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new Exception("Erro ao efetuar login"));
            if (loginRequest.getSenha().equals(user.getSenha())){
                    String Token = tokenService.generationToken(user);
                    return ResponseEntity.ok(Token);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
