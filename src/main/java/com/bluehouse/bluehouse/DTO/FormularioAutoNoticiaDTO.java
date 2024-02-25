package com.bluehouse.bluehouse.DTO;

import com.bluehouse.bluehouse.models.FuncionarioModel;
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
public class FormularioAutoNoticiaDTO {
    
    private UUID id;
    private UUID idFuncionario;
    private FuncionarioModel funcionario;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataOcorrido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReporte;
    private LocalTime horaOcorrido;
    private String tipoOcorrencia;
    private String local;
    private String descricao;
    private String estado;
}

