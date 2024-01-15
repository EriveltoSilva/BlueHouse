package com.bluehouse.bluehouse.controllers;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    /* Rotas Simples  - Retorno de Páginas */
    @GetMapping("/cadastrar")
    public String formCadastrarFuncionario() {
        return "funcionarios/cadastrar-funcionario";
    }

    @PostMapping("/cadastrar")
    public String cadastrarFuncionario(FuncionarioModel funcionario) {
        funcionarioService.criar(funcionario);
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/listar")
    public String listarFuncionarios(Model model) {
        List<FuncionarioModel> funcionarios = funcionarioService.listar();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionarios/listar-funcionarios";
    }
}
