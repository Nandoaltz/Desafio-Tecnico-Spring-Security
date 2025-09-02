package com.dev.simpleMvp.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Schema(name = "UserDTO", description = "Representa o modelo de que é exibido para o usuario após fazer uma requisição, serve como camada de abstração para proteger od dados no banco")
public class UserDTO{
    @Schema(description = "ID único do usuário", example = "1", required = true)
    private Long Id;
    @Schema(description = "Nome completo do usuário", example = "Nando Silva", required = true)
    private String nome;
    @Schema(description = "Email do usuário", example = "nando@email.com", required = true)
    private String email;
    @Schema(description = "Data de nascimento do usuário", example = "20/11/2004")
    private LocalDate dataNasc;
    @Schema(description = "Idade calculada com basa na diferença entre o instante atual com a data de nascimento do usuario", example = "20")
    private Integer idade;

    public UserDTO(Long id, String nome, String email, LocalDate dataNasc, Integer idade) {
        Id = id;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.idade = idade;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
