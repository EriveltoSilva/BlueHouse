package com.bluehouse.bluehouse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "turno")
public class TurnoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    
    @FutureOrPresent(message = "Você colocou uma data do passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataTurno;

    @NotNull(message = "A hora de início não pode ser nula")
    private LocalTime horaInicio;

    @NotNull(message = "A hora de fim não pode ser nula")
    private LocalTime horaFim;

    @NotBlank(message = "A área não pode estar em branco")
    private String area;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @NotNull(message = "O funcionário não pode ser nulo")
    private FuncionarioModel funcionario;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    @NotNull(message = "O horário não pode ser nulo")
    private HorarioModel horario;
}
