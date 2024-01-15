package com.bluehouse.bluehouse.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FuncionarioDto {
    @NotNull(message = "{PessoaDTO.nome.notnull}")
    private String nome;
    
    @NotNull(message = "{PessoaDTO.dataNascimento.notnull}")
    private LocalDate dataNascimento;

    @NotNull(message = "{FuncionarioDTO.cargo.notnull}")
    private String cargo;

    @NotNull(message = "{FuncionarioDTO.departamento.notnull}")
    private String departamento;

    @NotNull(message = "{FuncionarioDTO.email.notnull}")
    private String email;

    @NotNull(message = "{FuncionarioDTO.senha.notnull}")
    private String senha;

    @NotNull(message = "{FuncionarioDTO.endereco.notnull}")
    private String endereco;
}
