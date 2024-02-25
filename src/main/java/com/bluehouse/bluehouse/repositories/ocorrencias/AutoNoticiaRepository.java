package com.bluehouse.bluehouse.repositories.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutoNoticiaRepository extends JpaRepository<AutoNoticiaModel, UUID> {
    List<AutoNoticiaModel> findByFuncionario_NomeCompletoContainingIgnoreCase(String nome);
    List<AutoNoticiaModel> findByEstado(String estado);
}
