package com.dev.simpleMvp.demo.controller;

import java.util.List;

import com.dev.simpleMvp.demo.dto.mapper.UserMapper;
import com.dev.simpleMvp.demo.security.config.ConfigSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.simpleMvp.demo.dto.UserDTO;
import com.dev.simpleMvp.demo.entity.User;
import com.dev.simpleMvp.demo.services.UserService;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "User" , description = "Controlador do usuário com todas as rotas principais do usuário")
@SecurityRequirement(name = ConfigSecurity.SECURITY)
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @PostMapping
    @Operation(summary = "Rota de Cadastro",
            description = "Rota para cadastrar um novo usuário no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.map(user1));
    }

    @GetMapping
    @Operation(summary = "Rota para listar usuários",
            description = "Rota para listar todos os usuarios do banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuarios não encontrados")
    })
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rota para listar um usuário",
            description = "Rota para listar um unico usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário listado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    public ResponseEntity<UserDTO> findByIdUser(@PathVariable Long id){
        UserDTO uDto = userService.findByIdUsers(id);
        return ResponseEntity.ok(uDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Rota para atuaoizar usuários",
            description = "Rota para atualizar os campos que o usuario passar por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuarios não encontrados")
    })
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Rota para deletar um usuario",
    description = "Deleta um usuario do banco passando pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Falha ao deletar usuário")
    })
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario Deletado!");
    }
}
