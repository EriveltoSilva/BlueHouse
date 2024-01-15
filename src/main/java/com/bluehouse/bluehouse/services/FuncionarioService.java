package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
