package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OcorrenciaController {
    @GetMapping("ocorrencias/cadastrar")
    public String cadastrarOcorrencia() {
        return "ocorrencias/cadastrar-ocorrencia";
    }

    @GetMapping("ocorrencias/listar")
    public String listarOcorrencias() {
        return "ocorrencias/listar-ocorrencias";
    }
}
