package com.bluehouse.bluehouse.entities;

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
@Table(name = "postos")
@Getter
@Setter
@NoArgsConstructor
public class Posto {
    
    @Id
    @GeneratedValue
    private UUID idPosto;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "localizacao")
    private String localizacao;


    public Posto(String designacao,String descricao, String localizacao){
        this.designacao = designacao;
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public static class PostoBuilder {
        
        private String designacao;
        private String descricao;
        private String localizacao;

        public PostoBuilder designacao(String designacao){
            this.designacao = designacao;
            return this;
        }
        
        public PostoBuilder descricao(String descricao){
            this.descricao = descricao;
            return this;
        }
        
        public PostoBuilder localizacao(String localizacao){
            this.localizacao = localizacao;
            return this;
        }

        public Posto build(){
            return new Posto(designacao, descricao, localizacao);
        }
    } 
}
