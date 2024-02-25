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

    public List<AutoNoticiaModel> pesquisarPorNome(String nome) {
        return autoNoticiaRepository.findByFuncionario_NomeCompletoContainingIgnoreCase(nome);
    }
    
    public List<AutoNoticiaModel> pesquisarPorEstado(String estado) {
        return autoNoticiaRepository.findByEstado(estado);
    }
    
    public List<AutoNoticiaModel> pesquisarPorNomeOuEstado(String keyword) {
        // Verifica se o keyword é numérico (estado) ou alfabético (nome)
        if (keyword.equalsIgnoreCase("Activo") || keyword.equalsIgnoreCase("Finalizado")) {
            // Pesquisa por estado
            return autoNoticiaRepository.findByEstado(keyword);
        } else {
            // Pesquisa por nome
            return autoNoticiaRepository.findByFuncionario_NomeCompletoContainingIgnoreCase(keyword);
        }
    }

    public void eliminar( UUID id )
    {
        autoNoticiaRepository.deleteById(id);
    }

    public long getTotal() {
        return autoNoticiaRepository.count(); // Isso conta o número total 
    }
}
