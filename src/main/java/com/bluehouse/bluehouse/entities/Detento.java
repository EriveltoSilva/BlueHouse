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
@Table(name = "detentos")
@Getter
@Setter
@NoArgsConstructor
public class Detento {
    @Id
    @GeneratedValue
    private UUID idDetento;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "bi")
    private String bi;

    @Column(name = "endereco")
    private String endereco;

    public Detento(String nome,LocalDate dataNascimento, String telefone, String bi, String endereco){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.bi = bi;
        this.endereco = endereco;
    }
    
    //AplicaÃ§Ã£o do PadrÃ£o de projecto - Builder 
    public static class DetentoBuilder{
        
        private String nome;
        private LocalDate dataNascimento;
        private String telefone;
        private String bi;
        private String endereco;
        
        public DetentoBuilder nome(String nome){
            this.nome = nome;
            return this;
        }
        
        public DetentoBuilder dataNascimento(LocalDate dataNascimento){
            this.dataNascimento = dataNascimento;
            return this;
        }
        
        public DetentoBuilder telefone(String telefone){
            this.telefone = telefone;
            return this;
        }
        
        public DetentoBuilder bi(String bi){
            this.bi = bi;
            return this;
        }
        
        public DetentoBuilder endereco(String endereco){
            this.endereco = endereco;
            return this;
        }

        public Detento build(){
            return new Detento(nome, dataNascimento, telefone, bi, endereco);
        }
    }
}
