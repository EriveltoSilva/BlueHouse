package com.bluehouse.bluehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "funcionario")
public class FuncionarioModel  extends PessoaModel{
    
    @NotBlank(message = "O cargo não pode estar em branco")
    private String cargo;

    @NotBlank(message = "O departamento não pode estar em branco")
    private String departamento;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode estar em branco")
    private String email;

    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
}
