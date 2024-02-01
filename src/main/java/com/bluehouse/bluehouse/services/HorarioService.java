package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.HorarioModel;
import com.bluehouse.bluehouse.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public HorarioModel criar(HorarioModel novoHorario) {
        return horarioRepository.save(novoHorario);
    }

    public List<HorarioModel> listar() {
        return horarioRepository.findAll();
    }

    public List<HorarioModel> obterHorariosParaOMesEmCurso (int ano, int mes) {
        return horarioRepository.findByAnoEMes(ano,mes);
    }

    public HorarioModel editar(HorarioModel novoHorario) {
        return horarioRepository.save(novoHorario);
    }

    public Optional<HorarioModel> obterHorarioModel(UUID id)
    {
        return horarioRepository.findById(id);
    }

    public void eliminar( UUID id )
    {
        horarioRepository.deleteById(id);
    }

    public long getTotal() {
        return horarioRepository.count(); // Isso conta o número total 
    }

}
