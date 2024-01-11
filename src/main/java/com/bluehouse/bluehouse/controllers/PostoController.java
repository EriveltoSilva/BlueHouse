package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostoController {
    @GetMapping("postos/cadastrar")
    public String cadastrarPosto() {
        return "postos/cadastrar-posto";
    }

    @GetMapping("postos/listar")
    public String listarPostos() {
        return "postos/listar-postos";
    } 
}
