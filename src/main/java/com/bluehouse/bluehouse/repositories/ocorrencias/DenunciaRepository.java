package com.bluehouse.bluehouse.repositories.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DenunciaRepository extends JpaRepository<DenunciaModel, UUID> {
}
