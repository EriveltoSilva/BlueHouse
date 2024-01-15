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
@Table(name = "relatorios")
@Getter
@Setter
@NoArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue
    private UUID idRelatorio;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipoRelatorio")
    private String tipoRelatorio;
    
    @Column(name = "data")
    private LocalDate data;


    public Relatorio(String descricao,String tipoRelatorio, LocalDate data){
        this.descricao = descricao;
        this.tipoRelatorio = tipoRelatorio;
        this.data = data;
    }

    public static class RelatorioBuilder {
        
        private String descricao;
        private String tipoRelatorio;
        private LocalDate data;
        
        public RelatorioBuilder descricao(String descricao){
            this.descricao = descricao;
            return this;
        }

        public RelatorioBuilder tipoRelatorio(String tipoRelatorio){
            this.tipoRelatorio = tipoRelatorio;
            return this;
        }
        
        public RelatorioBuilder data(LocalDate data){
            this.data = data;
            return this;
        }

        public Relatorio build(){
            return new Relatorio(descricao, tipoRelatorio, data);
        }
    }
}
