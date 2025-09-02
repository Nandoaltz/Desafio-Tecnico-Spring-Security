package com.dev.simpleMvp.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "Representa os dados de um usuário no sistema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "1", required = true)
    private Long id;
    @Schema(description = "Nome completo do usuário", example = "Nando Silva", required = true)
    private String nome;

    @Column(unique = true)
    @Schema(description = "Email do usuário", example = "nando@email.com", required = true)
    private String email;

    @Schema(description = "Senha do do usuário", example = "12345")
    private String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento do usuário", example = "20/11/2004")
    private LocalDate dataNasc;
    @Schema(description = "Idade calculada com basa na diferença entre o instante atual com a data de nascimento do usuario", example = "20")
    private Integer idade;

}
