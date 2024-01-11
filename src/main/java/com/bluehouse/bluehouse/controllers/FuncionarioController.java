package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuncionarioController {

    @GetMapping("funcionarios/cadastrar")
    public String cadastrarFuncionario() {
        return "funcionarios/cadastrar-funcionario";
    }

    @GetMapping("funcionarios/listar")
    public String listarFuncionarios() {
        return "funcionarios/listar-funcionarios";
    }
}
