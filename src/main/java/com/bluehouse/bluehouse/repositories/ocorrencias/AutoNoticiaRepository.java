package com.bluehouse.bluehouse.repositories.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoNoticiaRepository extends JpaRepository<AutoNoticiaModel, UUID> {
}
