package com.bluehouse.bluehouse.controllers;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    /* Rotas Simples - Retorno de Páginas */
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

    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable("id") UUID id, Model model) {
        Optional<FuncionarioModel> funcionario = funcionarioService.obteFuncionarioModel(id);
        
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "funcionarios/editar-funcionario";
        } else {
            // Tratar o caso em que o funcionário não foi encontrado
            return "redirect:/funcionarios/listar";
        }
    }

    @PostMapping("/editar/{id}")
    public String editarFuncionarios(@PathVariable("id") UUID id, FuncionarioModel editFuncionarioModel ) {

        funcionarioService.editar(editFuncionarioModel);
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/obter/{id}")
    public String obterFuncionario(@PathVariable("id") UUID id, Model model) {
        Optional<FuncionarioModel> funcionario = funcionarioService.obteFuncionarioModel(id);
        model.addAttribute("funcionario", funcionario.orElse(new FuncionarioModel())); // Usando orElse para evitar null
        return "funcionarios/editar-funcionario";
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarFuncionario(@PathVariable("id") UUID id) {
        FuncionarioModel funcionario = funcionarioService.eliminar(id);

        if (funcionario != null) {
            return ResponseEntity.ok("Funcionário excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
