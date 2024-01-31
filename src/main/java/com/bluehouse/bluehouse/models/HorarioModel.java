package com.bluehouse.bluehouse.models;

import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "horario")
public class HorarioModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataTurno;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurnoModel> turnos = new ArrayList<TurnoModel>();
}

/*
    @NotBlank(message = "O motivo não pode estar em branco")
    private String motivo;

    @NotNull(message = "A data da aplicação da medida não pode estar em branco")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataAplicacao;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private FuncionarioModel adminAplicador;

    @ManyToOne
    @JoinColumn(name = "funcionario")
    private FuncionarioModel funcionario;

    @NotNull(message = "A data da aplicação da medida não pode estar em branco")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @NotNull(message = "A data da aplicação da medida não pode estar em branco")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataTermino;
*/