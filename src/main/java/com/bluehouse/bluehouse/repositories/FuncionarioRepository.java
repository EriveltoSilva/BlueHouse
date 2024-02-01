package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.FuncionarioModel;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {
    public FuncionarioModel findByEmail(String email);

    List<FuncionarioModel> findByNomeCompletoStartingWith(String nomeCompleto);
    List<FuncionarioModel> findByNomeCompletoContaining(String nomeCompleto);
    List<FuncionarioModel> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);    
}
