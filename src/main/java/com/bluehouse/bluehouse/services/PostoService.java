package com.bluehouse.bluehouse.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bluehouse.bluehouse.models.PostoModel;
import com.bluehouse.bluehouse.repositories.PostoRepository;

@Service
public class PostoService{
    @Autowired
    private PostoRepository postoRepository;

    public PostoModel criar(PostoModel novoPosto) {
        return postoRepository.save(novoPosto);
    }

    public List<PostoModel> listar() {
        return postoRepository.findAll();
    }

    public PostoModel editar(PostoModel novoPosto) {
        return postoRepository.save(novoPosto);
    }

    public Optional<PostoModel> obtePostoModel(UUID id)
    {
        return postoRepository.findById(id);
    }

    public PostoModel eliminar( UUID id ) 
    {
        Optional<PostoModel> eliminarPosto = obtePostoModel(id);
        if ( eliminarPosto.isEmpty() )
            return eliminarPosto.orElse(null);
            postoRepository.deleteById(id);
        return eliminarPosto.get();
    }

    public long getTotal() {
        return postoRepository.count(); // Isso conta o número total 
    }
}
