package com.bluehouse.bluehouse.services;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluehouse.bluehouse.models.DetentoModel;
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

    public DetentoModel eliminar( UUID id ) 
    {
        Optional<DetentoModel> eliminarDetento = obterDetentoModel(id);
        if ( eliminarDetento.isEmpty() )
            return eliminarDetento.orElse(null);
            detentoRepository.deleteById(id);
        return eliminarDetento.get();
    }
}
