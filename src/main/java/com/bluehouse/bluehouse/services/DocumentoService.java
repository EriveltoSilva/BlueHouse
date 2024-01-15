package com.bluehouse.bluehouse.services;

import java.util.UUID;

import com.bluehouse.bluehouse.models.DocumentoModel;

public class DocumentoService extends AbstractService<DocumentoModel, UUID> {
    @Override
    public DocumentoModel editar(UUID id, DocumentoModel entidade) {
       // entidade.setIdDocumento(id);
        return super.editar(id, entidade); 
    }
}
