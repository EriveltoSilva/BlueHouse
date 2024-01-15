package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.entities.Detento;

@Service
public class DetentoService extends AbstractService<Detento, UUID> {
    @Override
    public Detento editar(UUID id, Detento entidade) {
        entidade.setIdDetento(id);
        return super.editar(id, entidade); 
    }
}
