package com.bluehouse.bluehouse.services.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import com.bluehouse.bluehouse.repositories.ocorrencias.DenunciaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DenunciaService {
    @Autowired
    private final DenunciaRepository denunciaRepository;

    public DenunciaModel criar(DenunciaModel novoDenuncia) {
        return denunciaRepository.save(novoDenuncia);
    }

    public List<DenunciaModel> listar() {
        return denunciaRepository.findAll();
    }

    public DenunciaModel editar(DenunciaModel novoDenuncia) {
        return denunciaRepository.save(novoDenuncia);
    }

    public Optional<DenunciaModel> obterDenunciaModel(UUID id)
    {
        return denunciaRepository.findById(id);
    }

    
    public List<DenunciaModel> pesquisarPorNomeOuEstado(String keyword) {
        if (keyword.equalsIgnoreCase("Activo") || keyword.equalsIgnoreCase("Finalizado")) {
            // Pesquisa por estado
            return denunciaRepository.findByEstado(keyword);
        } else {
            // Pesquisa por nome
            return denunciaRepository.findByReportante_NomeCompletoContainingIgnoreCase(keyword);
        }
    }
    

    public void eliminar( UUID id )
    {
        denunciaRepository.deleteById(id);
    }

    public long getTotal() {
        return denunciaRepository.count(); // Isso conta o número total 
    }
}
