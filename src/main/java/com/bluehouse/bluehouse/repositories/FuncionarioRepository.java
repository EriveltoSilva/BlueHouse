package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    
}