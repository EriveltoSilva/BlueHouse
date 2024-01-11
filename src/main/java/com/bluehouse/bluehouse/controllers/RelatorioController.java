package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {
    @GetMapping("relatorios/escrever")
    public String escreverRelatorio() {
        return "relatorios/escrever-relatorio";
    }

    @GetMapping("relatorios/listar")
    public String listarRelatorios() {
        return "relatorios/listar-relatorios";
    }
}
