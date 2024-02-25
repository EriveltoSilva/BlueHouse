package com.bluehouse.bluehouse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    
    @Size(min=4, max=45)
    private String nomeCompleto;

    @NotNull(message = "A data de nascimento não pode estar em branco")
    @Past(message = "A data de nascimento deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @NotBlank(message = "O BI não pode estar em branco")
    // @Pattern(regexp = "[a-zA-Z0-9]{14}", message = "O BI deve conter 14 dígitos, incluindo duas letras.")
    private String bi;

    @Size(min=4, max=30)
    @NotBlank(message = "O endereço não pode estar em branco")
    private String endereco;

    @NotBlank(message = "O contacto não pode estar em branco")
    @Pattern(regexp = "\\d{9}", message = "O contacto deve conter 9 dígitos")
    private String contacto;

    @NotBlank(message = "O género não pode estar em branco")
    private String genero;
}
