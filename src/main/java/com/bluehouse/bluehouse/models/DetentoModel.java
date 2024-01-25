package com.bluehouse.bluehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private String nacionalidade;
    private String raca;
    private String estadoCivil;
    
    private String altura;
    private String email;
    private String observacao;
}
