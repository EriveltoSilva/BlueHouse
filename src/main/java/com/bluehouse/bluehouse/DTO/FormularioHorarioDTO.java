package com.bluehouse.bluehouse.DTO;

import com.bluehouse.bluehouse.models.FuncionarioModel;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @FutureOrPresent(message = "{horario.dataTurno.futureOrPresent}")
    @NotNull(message = "{horario.dataTurno.notNull}")
    private LocalDate dataTurno;

    @NotNull(message = "{horario.horaInicio.notNull}")
    private LocalTime horaInicio;

    @NotNull(message = "{horario.horaFim.notNull}")
    private LocalTime horaFim;

    @NotNull(message = "{horario.area.notNull}")
    private String area;
    private List<FuncionarioModel> funcionarios;
}
