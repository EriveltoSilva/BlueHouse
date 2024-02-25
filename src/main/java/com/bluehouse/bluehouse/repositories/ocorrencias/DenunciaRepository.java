package com.bluehouse.bluehouse.repositories.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DenunciaRepository extends JpaRepository<DenunciaModel, UUID> {
    List<DenunciaModel> findByReportante_NomeCompletoContainingIgnoreCase(String nome);
    List<DenunciaModel> findByEstado(String estado);
}
