package com.bluehouse.bluehouse.models.ocorrencias;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autonoticia")
public class AutoNoticiaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "A data do ocorrido e não foi preenchida")
    private Date dataOcorrido;

    private Date dataReporte;

    @NotNull(message = "A hora aproximada do ocorrido não foi preenchida")
    private LocalTime horaOcorrido;

    @NotBlank(message = "O  tipo de ocorrencia não foi preenchido")
    private String tipoOcorrencia;

    @NotBlank(message = "O campo do local não pode estar em branco")
    private String local;

    @NotBlank(message = "O campo da descrição não pode estar em branco")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioModel funcionario;
}
