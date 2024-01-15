package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.entities.Relatorio;

@Service
public class RelatorioService extends AbstractService<Relatorio, UUID> {
    @Override
    public Relatorio editar(UUID id, Relatorio entidade) {
        entidade.setIdRelatorio(id);
        return super.editar(id, entidade); 
    }
}
