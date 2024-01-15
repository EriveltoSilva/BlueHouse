package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.models.RelatorioModel;

@Service
public class RelatorioService extends AbstractService<RelatorioModel, UUID> {
    @Override
    public RelatorioModel editar(UUID id, RelatorioModel entidade) {
        //entidade.setIdRelatorio(id);
        return super.editar(id, entidade); 
    }
}
