package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.models.DetentoModel;
import java.util.List;


public interface DetentoRepository extends JpaRepository<DetentoModel, UUID>{
    List<DetentoModel> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);
    List<DetentoModel> findByBi(String bi);
}
