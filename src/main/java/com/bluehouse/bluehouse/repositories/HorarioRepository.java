package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.HorarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface HorarioRepository extends JpaRepository<HorarioModel, UUID> {
    @Query("SELECT h FROM HorarioModel h WHERE YEAR(h.dataTurno) = :ano AND MONTH(h.dataTurno) = :mes")
    List<HorarioModel> findByAnoEMes(@Param("ano") int ano, @Param("mes") int mes);
}
