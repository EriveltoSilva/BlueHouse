package com.bluehouse.bluehouse.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "ocorrencia")
public class OcorrenciaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
}
