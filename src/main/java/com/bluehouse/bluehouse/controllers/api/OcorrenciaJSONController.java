package com.bluehouse.bluehouse.controllers.api;

import java.util.List;

import com.bluehouse.bluehouse.DTO.FuncionarioDTO;
import com.bluehouse.bluehouse.DTO.ReportanteDTO;
import com.bluehouse.bluehouse.services.FuncionarioService;
import com.bluehouse.bluehouse.services.ReportanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OcorrenciaJSONController {
    @Autowired
    private ReportanteService reportanteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/ocorrencias/get-reportante")
    public List<ReportanteDTO> buscarReportantesPorNome(@RequestParam String nomeCompleto) {
        System.out.println(nomeCompleto);
        List<ReportanteDTO> reportantesEncontrados = reportanteService.buscarPorNome(nomeCompleto);
        return reportantesEncontrados;
    }

    @GetMapping("/ocorrencias/get-funcionario")
    public List<FuncionarioDTO> buscarFuncionariosPorNome(@RequestParam String nomeCompleto) {
        List<FuncionarioDTO> funcionariosEncontrados = funcionarioService.buscarPorNome(nomeCompleto);
        return funcionariosEncontrados;
    }
}
