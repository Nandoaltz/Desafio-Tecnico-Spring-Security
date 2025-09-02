package com.dev.simpleMvp.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "LoginRequest", description = "Representa o modelo de requisição para autenticação")
public class LoginRequest{
    @Schema(description = "Email do usuário", example = "nando@email.com", required = true)
    private String email;
    @Schema(description = "Senha do usuário", example = "12345", required = true)
    private String senha;
}
