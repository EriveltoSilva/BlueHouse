package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.PessoaModel;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> {

    @Query("SELECT p.genero, COUNT(p) FROM PessoaModel p GROUP BY p.genero")
    List<Object[]> contarPorGenero();
}
