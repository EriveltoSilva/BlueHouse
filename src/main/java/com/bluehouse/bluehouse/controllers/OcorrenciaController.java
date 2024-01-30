package com.bluehouse.bluehouse.controllers;

import java.util.List;

import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import com.bluehouse.bluehouse.services.ocorrencias.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OcorrenciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping("/ocorrencias/listar")
    public String listarOcorrencias(Model model) {
        List<DenunciaModel> denuncias = denunciaService.listar();
        model.addAttribute("denuncias", denuncias);
        return "ocorrencias/listar-ocorrencias";
    }
}
