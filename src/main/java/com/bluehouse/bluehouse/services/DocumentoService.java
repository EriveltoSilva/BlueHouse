package com.bluehouse.bluehouse.services;

import java.util.UUID;

import com.bluehouse.bluehouse.entities.Documento;

public class DocumentoService extends AbstractService<Documento, UUID> {
    @Override
    public Documento editar(UUID id, Documento entidade) {
        entidade.setIdDocumento(id);
        return super.editar(id, entidade); 
    }
}
