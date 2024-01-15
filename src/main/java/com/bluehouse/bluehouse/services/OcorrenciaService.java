package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.models.OcorrenciaModel;

@Service
public class OcorrenciaService extends AbstractService<OcorrenciaModel, UUID> {
    @Override
    public OcorrenciaModel editar(UUID id, OcorrenciaModel entidade) {
        //entidade.setIdOcorrencia(id);
        return super.editar(id, entidade); 
    }
}
