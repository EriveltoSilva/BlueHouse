package com.bluehouse.bluehouse.DTO;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioHorarioDTO {
    private UUID idHorario;
    private UUID idTurno;
    private UUID idFuncionario;//Para o funcionario a ser adicionado
    private LocalDate dataTurno;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String area;
    private List<FuncionarioModel> funcionarios;
}
