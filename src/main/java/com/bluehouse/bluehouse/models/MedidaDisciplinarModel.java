package com.bluehouse.bluehouse.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medidaDisciplinar")
public class MedidaDisciplinarModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    
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

    private boolean tempoIndefinido;

    @NotBlank(message = "Observações não podem estar em branco")
    private String observacoes;

}
