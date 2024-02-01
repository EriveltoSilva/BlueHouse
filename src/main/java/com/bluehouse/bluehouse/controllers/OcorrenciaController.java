package com.bluehouse.bluehouse.controllers;

import com.bluehouse.bluehouse.services.ocorrencias.DenunciaService;
import com.bluehouse.bluehouse.services.ocorrencias.QueixaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OcorrenciaController {

    @Autowired
    private DenunciaService denunciaService;
    
    @Autowired
    private QueixaService queixaService;

    @GetMapping("/ocorrencias/listar")
    public String listarOcorrencias(Model model) {
        model.addAttribute("denuncias", denunciaService.listar());
        model.addAttribute("queixas", queixaService.listar());
        return "ocorrencias/listar-ocorrencias";
    }
}
