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

    private Date dataTurno;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private String area;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioModel funcionario;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private HorarioModel horario;
}
