package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HorarioController {
    
    @GetMapping("horarios/cadastrar")
    public String cadastrarHorario() {
        return "horarios/cadastrar-horario";
    }

    @GetMapping("horarios/listar")
    public String listarHorarios() {
        return "horarios/listar-horarios";
    }
}
