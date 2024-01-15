package com.bluehouse.bluehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String cargo;
    private String departamento;
    private String email;
    private String senha;
}
