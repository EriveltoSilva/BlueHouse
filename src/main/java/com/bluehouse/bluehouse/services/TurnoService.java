package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.TurnoModel;
import com.bluehouse.bluehouse.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;

@Service
public class TurnoService {
    @Autowired
    private  TurnoRepository turnoRepository;

    public TurnoModel criar(TurnoModel novoTurno) {
        return turnoRepository.save(novoTurno);
    }

    public List<TurnoModel> listar() {
        return turnoRepository.findAll();
    }

    public TurnoModel editar(TurnoModel novoTurno) {
        return turnoRepository.save(novoTurno);
    }

    public Optional<TurnoModel> obterTurnoModel(UUID id)
    {
        return turnoRepository.findById(id);
    }

    public void eliminar( UUID id )
    {
        turnoRepository.deleteById(id);
    }

    public List<TurnoModel> obterTurnosParaOMesEmCurso(int ano, int mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = LocalDate.of(ano, mes, dataInicio.lengthOfMonth());
        Date inicioMes = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fimMes = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return turnoRepository.findByDataTurnoBetween(inicioMes, fimMes);
    }

    public List<TurnoModel> obterTurnosOrdenadosPorMesEmCurso(int ano, int mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = LocalDate.of(ano, mes, dataInicio.lengthOfMonth());
        Date inicioMes = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fimMes = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return turnoRepository.findByDataTurnoBetweenOrderByDataTurno(inicioMes, fimMes);
    }

    public long getTotal() {
        return turnoRepository.count(); // Isso conta o número total 
    }

}
