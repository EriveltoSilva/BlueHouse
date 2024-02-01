package com.bluehouse.bluehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detento")
public class DetentoModel  extends PessoaModel {

    @NotBlank(message = "A nacionalidade não pode estar em branco")
    private String nacionalidade;
    @NotBlank(message = "A raca não pode estar em branco")
    private String raca;
    @NotBlank(message = "O estado civil não pode estar em branco")
    private String estadoCivil;
    
    @NotBlank(message = "A altura não pode estar em branco")
    private String altura;

    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "O email deve conter '@' e '.'.")
    @NotBlank
    private String email;

    @NotBlank(message = "O email não pode estar em branco")
    private String observacao;
}
