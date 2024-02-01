package com.bluehouse.bluehouse.DTO;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioQueixaDTO {
    private UUID id;
    //Atributos do Reportante
    private String nomeCompleto;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String bi;
    private String email;
    private String endereco;
    private String contacto;
    private String genero;


    //Atributos da Queixa
    

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataOcorrido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataQueixa;
    private LocalTime horaOcorrido;
    private String tipoOcorrencia;
    private String descricao;
}

