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
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue
    private UUID idFuncionario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "endereco")
    private String endereco;

    public Funcionario(String nome,LocalDate dataNascimento, String cargo, String departamento, String email, String senha, String endereco){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.departamento = departamento;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }
    
    //AplicaÃ§Ã£o do PadrÃ£o de projecto - Builder 
    public static class FuncionarioBuilder{
        
        private String nome;
        private LocalDate dataNascimento;
        private String cargo;
        private String departamento;
        private String email;
        private String senha;
        private String endereco;
        
        public FuncionarioBuilder nome(String nome){
            this.nome = nome;
            return this;
        }
        
        public FuncionarioBuilder dataNascimento(LocalDate dataNascimento){
            this.dataNascimento = dataNascimento;
            return this;
        }
        
        public FuncionarioBuilder cargo(String cargo){
            this.cargo = cargo;
            return this;
        }
        
        public FuncionarioBuilder departamento(String departamento){
            this.departamento = departamento;
            return this;
        }
        
        public FuncionarioBuilder email(String email){
            this.email = email;
            return this;
        }
        
        public FuncionarioBuilder senha(String senha){
            this.senha = senha;
            return this;
        }
        
        public FuncionarioBuilder endereco(String endereco){
            this.endereco = endereco;
            return this;
        }

        public Funcionario build(){
            return new Funcionario(nome, dataNascimento, cargo, departamento, email, senha, endereco);
        }
    }
}
