package com.bluehouse.bluehouse.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.PostoModel;
import com.bluehouse.bluehouse.services.PostoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/postos")
public class PostoController {

    @Autowired
    private PostoService postoService;

    /* Rotas Simples - Retorno de Páginas*/
    @GetMapping("/cadastrar")
    public String formCadastrarPosto(Model model) {
        model.addAttribute("posto", new PostoModel());
        return "postos/cadastrar-posto";
    }
    @PostMapping("/cadastrar")
    public String cadastrarPosto(@Valid @ModelAttribute ("posto") PostoModel posto, BindingResult bResult, Model model, RedirectAttributes redirectAttributes) {
        if(bResult.hasErrors())
        {
            model.addAttribute("posto", posto);
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar posto");
            return "postos/cadastrar-posto";
        }
        else {
            postoService.criar(posto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Posto cadastrado com sucesso");
            return "redirect:/postos/listar";
        }
    }

    @GetMapping("/listar")
    public String listarPostos(Model model) {
        List<PostoModel> postos = postoService.listar();
        model.addAttribute("postos", postos);
        return "postos/listar-postos";
    }    
}
