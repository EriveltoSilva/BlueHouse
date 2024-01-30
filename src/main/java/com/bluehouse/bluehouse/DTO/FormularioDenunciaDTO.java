package com.bluehouse.bluehouse.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioDenunciaDTO {

    //Atributos do Reportante
    private String nomeCompleto;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String bi;
    private String email;
    private String endereco;
    private String contacto;
    private String genero;


    //Atributos da Denúncia
    

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataOcorrido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDenuncia;
    private LocalTime horaOcorrido;
    private String tipoOcorrencia;
    private String descricao;
}

