package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.models.RelatorioModel;

public interface RelatorioRepository extends JpaRepository<RelatorioModel, UUID>{
    
}
