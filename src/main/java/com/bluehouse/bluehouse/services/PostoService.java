package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.entities.Posto;

@Service
public class PostoService extends AbstractService<Posto, UUID> {
    @Override
    public Posto editar(UUID id, Posto entidade) {
        entidade.setIdPosto(id);
        return super.editar(id, entidade); 
    }
}
