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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    public String cadastrarMedidaDisciplinar(@Valid @ModelAttribute ("medidaDisciplinar") MedidaDisciplinarModel medidaDisciplinar, BindingResult bResult, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        if(bResult.hasErrors())
        {
            model.addAttribute("medidaDisciplinar", medidaDisciplinar);
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao adicionar medida disciplinar");
            return "medidasDisciplinares/registrar-medidaDisciplinar";
        }
        else {
            medidaDisciplinar.setAdminAplicador((FuncionarioModel)authentication.getPrincipal());
            medidaDisciplinarService.criar(medidaDisciplinar);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Medida disciplinar adicionada com sucesso");
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

    @GetMapping("/pesquisar")
    public String pesquisarFuncionarioComMedida(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<MedidaDisciplinarModel> resultadosMedidaDisciplinares;
        if (keyword != null) {
            resultadosMedidaDisciplinares = medidaDisciplinarService.pesquisarPorNome(keyword);
        } else {
            resultadosMedidaDisciplinares = new ArrayList<>(); 
        }
        model.addAttribute("resultadosMedidaDisciplinares", resultadosMedidaDisciplinares);
        return "medidasDisciplinares/listar-pesquisas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedidaDisciplinar(@PathVariable("id") UUID id) {
        medidaDisciplinarService.eliminar(id);
        return "redirect:/medidasDisciplinares/listar";
    }
}
