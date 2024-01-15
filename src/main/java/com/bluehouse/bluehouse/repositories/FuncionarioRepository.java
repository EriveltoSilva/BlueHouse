package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.FuncionarioModel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {
}
