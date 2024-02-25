package com.bluehouse.bluehouse.services;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.models.DetentoModel;
import com.bluehouse.bluehouse.models.ocorrencias.QueixaModel;
import com.bluehouse.bluehouse.repositories.DetentoRepository;

@Service
public class DetentoService{

    @Autowired
    private DetentoRepository detentoRepository;

    public DetentoModel criar(DetentoModel novoDetento) {
        return detentoRepository.save(novoDetento);
    }

    public List<DetentoModel> listar() {
        return detentoRepository.findAll();
    }

    public DetentoModel editar(DetentoModel novoDetento) {
        return detentoRepository.save(novoDetento);
    }

    public Optional<DetentoModel> obterDetentoModel(UUID id)
    {
        return detentoRepository.findById(id);
    }

    public List<DetentoModel> pesquisarPorNomeOuBi(String keyword) {
        // Verifica se a palavra contém espaços em branco
        boolean containsSpaces = keyword.contains(" ");
        List<DetentoModel> resultado;
        if (containsSpaces) {
            resultado = detentoRepository.findByNomeCompletoContainingIgnoreCase(keyword);
        } else {
            resultado = detentoRepository.findByBi(keyword);
        }
        return resultado;
    }

    public void eliminar( UUID id ) 
    {
        detentoRepository.deleteById(id);;
    }

    public long getTotal() {
        return detentoRepository.count(); // Isso conta o número total 
    }
}
