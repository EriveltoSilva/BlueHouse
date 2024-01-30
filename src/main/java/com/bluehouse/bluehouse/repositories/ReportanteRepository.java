package com.bluehouse.bluehouse.repositories;

import com.bluehouse.bluehouse.models.ReportanteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportanteRepository extends JpaRepository<ReportanteModel, UUID> {
}
