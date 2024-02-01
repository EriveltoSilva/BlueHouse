package com.bluehouse.bluehouse.controllers;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String formCadastrarFuncionario(Model model) {
        model.addAttribute("funcionario", new FuncionarioModel());
        return "funcionarios/cadastrar-funcionario";
    }

    @PostMapping("/cadastrar")
    public String cadastrarFuncionario(@Valid @ModelAttribute ("funcionario") FuncionarioModel funcionario, BindingResult bResult, Model model, RedirectAttributes redirectAttributes) {
        if(bResult.hasErrors())
        {
            model.addAttribute("funcionario", funcionario);
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar funcionario");
            return "funcionarios/cadastrar-funcionario";
        }
        else {
            funcionarioService.criar(funcionario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Funcionario cadastrado com sucesso");
            return "redirect:/funcionarios/listar";
        }
    }

    @GetMapping("/listar")
    public String listarFuncionarios(Model model) {
        List<FuncionarioModel> funcionarios = funcionarioService.listar();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionarios/listar-funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable("id") UUID id, Model model) {
        Optional<FuncionarioModel> funcionario = funcionarioService.obterFuncionarioModel(id);
        
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "funcionarios/editar-funcionario";
        } else {
            // Tratar o caso em que o funcionário não foi encontrado
            return "redirect:/funcionarios/listar";
        }
    }
    
    @PostMapping("/editar/{id}")
    public String editarFuncionarios(@PathVariable("id") UUID id,@Valid @ModelAttribute ("funcionario") FuncionarioModel editFuncionarioModel, BindingResult bResult, Model model, RedirectAttributes redirectAttributes ) {
        if(bResult.hasErrors())
        {
            model.addAttribute("funcionario", editFuncionarioModel);
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao editar funcionario");
            return "funcionarios/cadastrar-funcionario";
        }
        else{
            funcionarioService.editar(editFuncionarioModel);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Funcionario editado com sucesso");
            return "redirect:/funcionarios/listar";
        }
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFuncionario(@PathVariable("id") UUID id, Model model) {
        Optional<FuncionarioModel> funcionario = funcionarioService.obterFuncionarioModel(id);
        model.addAttribute("funcionario", funcionario.orElse(new FuncionarioModel())); // Usando orElse para evitar null
        return "funcionarios/detalhes-funcionario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFuncionario(@PathVariable("id") UUID id) {
        funcionarioService.eliminar(id);
        return "redirect:/funcionarios/listar";
    }
}
