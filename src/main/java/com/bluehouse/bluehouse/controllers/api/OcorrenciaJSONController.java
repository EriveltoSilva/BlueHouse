package com.bluehouse.bluehouse.controllers.api;

import java.util.List;
import com.bluehouse.bluehouse.DTO.ReportanteDTO;
import com.bluehouse.bluehouse.services.ReportanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OcorrenciaJSONController {
    @Autowired
    private ReportanteService reportanteService;

    @GetMapping("/ocorrencias/get-reportante")
    public List<ReportanteDTO> buscarReportantesPorNome(@RequestParam String nomeCompleto) {
        System.out.println(nomeCompleto);
        List<ReportanteDTO> reportantesEncontrados = reportanteService.buscarPorNome(nomeCompleto);
        return reportantesEncontrados;
    }
}
