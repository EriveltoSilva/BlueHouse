package com.bluehouse.bluehouse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.entities.Posto;
import com.bluehouse.bluehouse.models.PostoModel;

public interface PostoRepository extends JpaRepository<PostoModel, UUID>{

}
