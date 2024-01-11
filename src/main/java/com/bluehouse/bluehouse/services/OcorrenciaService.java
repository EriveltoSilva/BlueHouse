package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.entities.Ocorrencia;

@Service
public class OcorrenciaService extends AbstractService<Ocorrencia, UUID> {
    @Override
    public Ocorrencia editar(UUID id, Ocorrencia entidade) {
        entidade.setIdOcorrencia(id);
        return super.editar(id, entidade); 
    }
}
