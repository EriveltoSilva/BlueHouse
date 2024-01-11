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
@Table(name = "documentos")
@Getter
@Setter
@NoArgsConstructor
public class Documento {
   @Id
    @GeneratedValue
    private UUID idDocumento;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "remetente")
    private String remetente;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "data")
    private LocalDate data;


    public Documento(String designacao,String conteudo, String remetente, String destinatario, String tipo, LocalDate data){
        this.designacao = designacao;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.tipo = tipo;
        this.data = data;
    }

    public static class DocumentacaoBuilder{
        
        private String designacao;
        private String conteudo;
        private String remetente;
        private String destinatario;
        private String tipo;
        private LocalDate data;
        
        public DocumentacaoBuilder designacao(String designacao){
            this.designacao = designacao;
            return this;
        }
        
        public DocumentacaoBuilder conteudo(String conteudo){
            this.conteudo = conteudo;
            return this;
        }
        
        public DocumentacaoBuilder remetente(String remetente){
            this.remetente = remetente;
            return this;
        }
        
        public DocumentacaoBuilder destinatario(String destinatario){
            this.destinatario = destinatario;
            return this;
        }
        
        public DocumentacaoBuilder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }

        public DocumentacaoBuilder data(LocalDate data){
            this.data = data;
            return this;
        }

        public Documento build(){
            return new Documento(designacao, conteudo, remetente, destinatario, tipo, data);
        }
    } 
}
