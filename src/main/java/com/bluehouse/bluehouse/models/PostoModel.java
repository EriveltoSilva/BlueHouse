package com.bluehouse.bluehouse.models;


import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posto")
@Inheritance(strategy = InheritanceType.JOINED)
public class PostoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O posto não pode estar em branco")
    @NotNull
    private String nomePosto;

    @NotBlank(message = "A localização não pode estar em branco")
    @NotNull
    private String localizacao;

    @NotBlank(message = "A descrição não pode estar em branco")
    @NotNull
    private String descricao;
}
