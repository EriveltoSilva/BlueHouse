package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.PessoaModel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> {
}
