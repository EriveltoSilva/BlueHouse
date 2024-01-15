package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioModel criar(FuncionarioModel novoFuncionario) {
        return funcionarioRepository.save(novoFuncionario);
    }

    public List<FuncionarioModel> listar() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel editar(FuncionarioModel novoFuncionario) {
        return funcionarioRepository.save(novoFuncionario);
    }

    public Optional<FuncionarioModel> obterFuncionarioModel(UUID id)
    {
        return funcionarioRepository.findById(id);
    }

    public FuncionarioModel eliminar( UUID id ) 
    {
        Optional<FuncionarioModel> eliminarFuncionario = obterFuncionarioModel(id);
        if ( eliminarFuncionario.isEmpty() )
            return eliminarFuncionario.orElse(null);
        funcionarioRepository.deleteById(id);
        return eliminarFuncionario.get();
    }
}
