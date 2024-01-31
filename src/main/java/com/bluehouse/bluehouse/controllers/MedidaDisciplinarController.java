package com.bluehouse.bluehouse.controllers;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.MedidaDisciplinarModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import com.bluehouse.bluehouse.services.MedidaDisciplinarService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/medidasDisciplinares")
public class MedidaDisciplinarController {

    @Autowired
    private MedidaDisciplinarService medidaDisciplinarService;

    @Autowired
    private FuncionarioService funcionarioService;

    /* Rotas Simples - Retorno de Páginas */
    @GetMapping("/registrar")
    public String formCadastrarMedidaDisciplinar(Model model) {
        List<FuncionarioModel> funcionarios = funcionarioService.listar();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("medidaDisciplinar", new MedidaDisciplinarModel());
        return "medidasDisciplinares/registrar-medidaDisciplinar";
    }

    @PostMapping("/registrar")
    public String cadastrarMedidaDisciplinar(@Valid @ModelAttribute ("medidaDisciplinar") MedidaDisciplinarModel medidaDisciplinar, BindingResult bResult, Model model, Authentication authentication) {
        if(bResult.hasErrors())
        {
            model.addAttribute("medidaDisciplinar", medidaDisciplinar);
            return "medidasDisciplinares/registrar-medidaDisciplinar";
        }
        else {
            medidaDisciplinar.setAdminAplicador((FuncionarioModel)authentication.getPrincipal());
            medidaDisciplinarService.criar(medidaDisciplinar);
            return "redirect:/medidasDisciplinares/listar";
        }
    }

    @GetMapping("/listar")
    public String listarMedidaDisciplinars(Model model) {
        List<MedidaDisciplinarModel> medidasDisciplinares = medidaDisciplinarService.listar();
        model.addAttribute("medidasDisciplinares", medidasDisciplinares);
        return "medidasDisciplinares/listar-medidasDisciplinares";
    }

    @GetMapping("/editar/{id}")
    public String editarMedidaDisciplinarForm(@PathVariable("id") UUID id, Model model) {
        Optional<MedidaDisciplinarModel> medidaDisciplinar = medidaDisciplinarService.obterMedidaDisciplinarModel(id);
        
        if (medidaDisciplinar.isPresent()) {
            model.addAttribute("medidaDisciplinar", medidaDisciplinar.get());
            return "medidasDisciplinares/editar-medidaDisciplinar";
        } else {
            return "redirect:/medidasDisciplinares/listar";
        }
    }
    
    @PostMapping("/editar/{id}")
    public String editarMedidaDisciplinar(@PathVariable("id") UUID id, MedidaDisciplinarModel editMedidaDisciplinarModel ) {

        medidaDisciplinarService.editar(editMedidaDisciplinarModel);
        return "redirect:/medidasDisciplinares/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesMedidaDisciplinar(@PathVariable("id") UUID id, Model model) {
        Optional<MedidaDisciplinarModel> medidaDisciplinar = medidaDisciplinarService.obterMedidaDisciplinarModel(id);
        model.addAttribute("medidaDisciplinar", medidaDisciplinar.orElse(new MedidaDisciplinarModel()));
        return "medidasDisciplinares/detalhes-medidaDisciplinar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedidaDisciplinar(@PathVariable("id") UUID id) {
        medidaDisciplinarService.eliminar(id);
        return "redirect:/medidasDisciplinares/listar";
    }
}
