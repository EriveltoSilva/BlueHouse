package com.bluehouse.bluehouse.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.entities.Funcionario;

@Service
public class FuncionarioService extends AbstractService<Funcionario, UUID> {
    @Override
    public Funcionario editar(UUID id, Funcionario entidade) {
        entidade.setIdFuncionario(id);
        return super.editar(id, entidade); 
    }
}
