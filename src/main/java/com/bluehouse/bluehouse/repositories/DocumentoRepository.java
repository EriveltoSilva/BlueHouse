package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.entities.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, UUID> {
    
}
