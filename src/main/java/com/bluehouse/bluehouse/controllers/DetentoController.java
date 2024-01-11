package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetentoController {
    @GetMapping("/detentos/cadastrar")
    public String cadastrarDetento()
    {
        return "detentos/cadastrar-detento";
    }
    @GetMapping("/detentos/listar")
    public String listarDetentos()
    {
        return "detentos/listar-detentos";
    }
}
