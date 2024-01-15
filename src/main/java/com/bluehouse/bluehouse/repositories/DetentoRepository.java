package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.models.DetentoModel;

public interface DetentoRepository extends JpaRepository<DetentoModel, UUID>{
    
}
