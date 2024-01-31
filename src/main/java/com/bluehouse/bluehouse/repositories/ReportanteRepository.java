package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.ReportanteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportanteRepository extends JpaRepository<ReportanteModel, UUID> {
    List<ReportanteModel> findByNomeCompletoStartingWith(String nomeCompleto);
    List<ReportanteModel> findByNomeCompletoContaining(String nomeCompleto);
    List<ReportanteModel> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);
}
