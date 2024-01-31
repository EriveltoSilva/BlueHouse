package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.ReportanteModel;
import com.bluehouse.bluehouse.DTO.ReportanteDTO;
import com.bluehouse.bluehouse.repositories.ReportanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportanteService{
    
    @Autowired
    private final ReportanteRepository reportanteRepository;

    public ReportanteModel criar(ReportanteModel novoReportante) {
        return reportanteRepository.save(novoReportante);
    }

    public List<ReportanteModel> listar() {
        return reportanteRepository.findAll();
    }

    public ReportanteModel editar(ReportanteModel novoReportante) {
        return reportanteRepository.save(novoReportante);
    }

    public Optional<ReportanteModel> obterReportanteModel(UUID id)
    {
        return reportanteRepository.findById(id);
    }

    public void eliminar( UUID id ) 
    {
        reportanteRepository.deleteById(id);
    }

    public List<ReportanteDTO> buscarPorNome(String nomeCompleto) {
        List<ReportanteModel> reportantes = reportanteRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto);
        return reportantes.stream()
                .map(ReportanteDTO::fromReportante)
                .collect(Collectors.toList());
    }

}
