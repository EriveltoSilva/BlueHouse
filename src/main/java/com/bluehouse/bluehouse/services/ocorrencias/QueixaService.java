package com.bluehouse.bluehouse.services.ocorrencias;

import com.bluehouse.bluehouse.models.ocorrencias.QueixaModel;
import com.bluehouse.bluehouse.repositories.ocorrencias.QueixaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueixaService {
    @Autowired
    private final QueixaRepository queixaRepository;

    public QueixaModel criar(QueixaModel novaQueixa) {
        return queixaRepository.save(novaQueixa);
    }

    public List<QueixaModel> listar() {
        return queixaRepository.findAll();
    }

    public QueixaModel editar(QueixaModel novaQueixa) {
        return queixaRepository.save(novaQueixa);
    }

    public Optional<QueixaModel> obterQueixaModel(UUID id)
    {
        return queixaRepository.findById(id);
    }

    public void eliminar( UUID id )
    {
        queixaRepository.deleteById(id);
    }
}
