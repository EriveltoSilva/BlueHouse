package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.MedidaDisciplinarModel;
import com.bluehouse.bluehouse.repositories.MedidaDisciplinarRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedidaDisciplinarService {
    
    @Autowired
    private MedidaDisciplinarRepository medidaDisciplinarRepository;

    public MedidaDisciplinarModel criar(MedidaDisciplinarModel novoMedidaDisciplinar) {
        return medidaDisciplinarRepository.save(novoMedidaDisciplinar);
    }

    public List<MedidaDisciplinarModel> listar() {
        return medidaDisciplinarRepository.findAll();
    }

    public MedidaDisciplinarModel editar(MedidaDisciplinarModel novoMedidaDisciplinar) {
        return medidaDisciplinarRepository.save(novoMedidaDisciplinar);
    }

    public Optional<MedidaDisciplinarModel> obterMedidaDisciplinarModel(UUID id)
    {
        return medidaDisciplinarRepository.findById(id);
    }

    public void eliminar( UUID id ) 
    {
        medidaDisciplinarRepository.deleteById(id);;
    }

    public long getTotal() {
        return medidaDisciplinarRepository.count(); // Isso conta o número total 
    }
}
