package com.bluehouse.bluehouse.services.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import com.bluehouse.bluehouse.repositories.ocorrencias.AutoNoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutoNoticiaService {
    @Autowired
    private final AutoNoticiaRepository autoNoticiaRepository;

    public AutoNoticiaModel criar(AutoNoticiaModel novoAutoNoticia) {
        return autoNoticiaRepository.save(novoAutoNoticia);
    }

    public List<AutoNoticiaModel> listar() {
        return autoNoticiaRepository.findAll();
    }

    public AutoNoticiaModel editar(AutoNoticiaModel novoAutoNoticia) {
        return autoNoticiaRepository.save(novoAutoNoticia);
    }

    public Optional<AutoNoticiaModel> obterAutoNoticiaModel(UUID id)
    {
        return autoNoticiaRepository.findById(id);
    }

    public void eliminar( UUID id )
    {
        autoNoticiaRepository.deleteById(id);
    }
}
