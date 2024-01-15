package com.bluehouse.bluehouse.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class PessoaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    private String nomeCompleto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;
    private String bi;

    private String endereco;
    private String contacto;
    private String genero;

}
