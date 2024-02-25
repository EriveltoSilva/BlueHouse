package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.models.PostoModel;
import java.util.List;


public interface PostoRepository extends JpaRepository<PostoModel, UUID>{
    List<PostoModel> findByNomePosto(String nomePosto);
}
