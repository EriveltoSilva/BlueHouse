package com.bluehouse.bluehouse.repositories.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.QueixaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QueixaRepository extends JpaRepository<QueixaModel, UUID> {
    List<QueixaModel> findByReportante_NomeCompletoContainingIgnoreCase(String nome);
    List<QueixaModel> findByEstado(String estado);
}
