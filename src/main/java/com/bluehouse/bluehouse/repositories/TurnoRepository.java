package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.TurnoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TurnoRepository extends JpaRepository<TurnoModel, UUID> {
    List<TurnoModel> findByDataTurnoBetween(Date dataInicio, Date dataFim);

    @Query("SELECT t FROM TurnoModel t WHERE t.dataTurno BETWEEN :dataInicio AND :dataFim ORDER BY t.dataTurno")
    List<TurnoModel> findByDataTurnoBetweenOrderByDataTurno(Date dataInicio, Date dataFim);
    List<TurnoModel> findByFuncionario_NomeCompletoContainingIgnoreCase(String nomeCompleto);
 }
