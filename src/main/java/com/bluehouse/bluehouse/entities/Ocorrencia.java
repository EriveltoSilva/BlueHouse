package com.bluehouse.bluehouse.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ocorrencias")
@Getter
@Setter
@NoArgsConstructor
public class Ocorrencia {
    @Id
    @GeneratedValue
    private UUID idOcorrencia;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "remetente")
    private String remetente;

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "tipoOcorrencia")
    private String tipoOcorrencia;

    @Column(name = "data")
    private LocalDate data;


    public Ocorrencia(String designacao,String descricao, String remetente, Boolean estado, String tipoOcorrencia, LocalDate data){
        this.designacao = designacao;
        this.descricao = descricao;
        this.remetente = remetente;
        this.estado = estado;
        this.tipoOcorrencia = tipoOcorrencia;
        this.data = data;
    }

    public static class OcorrenciaBuilder{
        
        private String designacao;
        private String descricao;
        private String remetente;
        private Boolean estado;
        private String tipoOcorrencia;
        private LocalDate data;
        
        public OcorrenciaBuilder designacao(String designacao){
            this.designacao = designacao;
            return this;
        }
        
        public OcorrenciaBuilder descricao(String descricao){
            this.descricao = descricao;
            return this;
        }
        
        public OcorrenciaBuilder remetente(String remetente){
            this.remetente = remetente;
            return this;
        }
        
        public OcorrenciaBuilder estado(Boolean estado){
            this.estado = estado;
            return this;
        }
        
        public OcorrenciaBuilder tipo(String tipoOcorrencia){
            this.tipoOcorrencia = tipoOcorrencia;
            return this;
        }

        public OcorrenciaBuilder data(LocalDate data){
            this.data = data;
            return this;
        }

        public Ocorrencia build(){
            return new Ocorrencia(designacao, descricao, remetente, estado, tipoOcorrencia, data);
        }
    } 
}
