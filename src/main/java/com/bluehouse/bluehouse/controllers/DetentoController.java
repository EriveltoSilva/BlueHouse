package com.bluehouse.bluehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bluehouse.bluehouse.models.DetentoModel;
import com.bluehouse.bluehouse.services.DetentoService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/detentos")
public class DetentoController {

    @Autowired
    private DetentoService detentoService;

    /* Rotas Simples - Retorno de Páginas */
    @GetMapping("/cadastrar")
    public String formCadastrarDetento(Model model){
        model.addAttribute("detento", new DetentoModel());
        return "detentos/cadastrar-detento";
    }

    @PostMapping("/cadastrar")
    public String cadastrarDetento(@Valid @ModelAttribute ("detento")DetentoModel detento, BindingResult bResult, Model model, RedirectAttributes redirectAttributes) {
        if(bResult.hasErrors())
        {
            model.addAttribute("detento", detento);
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar detento");
            return "detentos/cadastrar-detento";
        }
        else {
            detentoService.criar(detento);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Detento cadastrado com sucesso");
            return "redirect:/detentos/listar";
        }
    }

    @GetMapping("/listar")
    public String listarDetentos(Model model){
        List<DetentoModel> detentos = detentoService.listar();
        model.addAttribute("detentos", detentos);
        return "detentos/listar-detentos";
    }

    @GetMapping("/editar/{id}")
    public String editarDetentoForm(@PathVariable("id") UUID id, Model model) {
        Optional<DetentoModel> detento = detentoService.obterDetentoModel(id);
        
        if (detento.isPresent()) {
            model.addAttribute("detento", detento.get());
            return "detentos/editar-detento";
        } else {
            // Tratar o caso em que o detentos não foi encontrado
            return "redirect:/detentos/listar";
        }
    }

    @PostMapping("/editar/{id}")
    public String editarDetentos(@PathVariable("id") UUID id, DetentoModel editDetentoModel, RedirectAttributes redirectAttributes ) {

        
        detentoService.editar(editDetentoModel);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Detento editado com sucesso");
        return "redirect:/detentos/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesDetentoForm(@PathVariable("id") UUID id, Model model) {
        Optional<DetentoModel> detento = detentoService.obterDetentoModel(id);
        model.addAttribute("detento", detento.orElse(new DetentoModel()));
        return "detentos/detalhes-detento";
    }

     @GetMapping("/pesquisar")
    public String pesquisarDetento(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<DetentoModel> resultadoDetento;
        if (keyword != null) {
            resultadoDetento = detentoService.pesquisarPorNomeOuBi(keyword);
        } else {
            resultadoDetento = new ArrayList<>(); 
        }
        model.addAttribute("resultadoDetento", resultadoDetento);
        return "detentos/listar-pesquisas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetento(@PathVariable("id") UUID id) {
        detentoService.eliminar(id);
        return "redirect:/detentos/listar";
    }
}
