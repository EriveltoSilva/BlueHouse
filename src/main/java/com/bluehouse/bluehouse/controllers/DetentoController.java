package com.bluehouse.bluehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluehouse.bluehouse.models.DetentoModel;
import com.bluehouse.bluehouse.services.DetentoService;

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
    public String formCadastrarDetento(){
        return "detentos/cadastrar-detento";
    }

    @PostMapping("/cadastrar")
    public String cadastrarDetento(DetentoModel detento) {
        detentoService.criar(detento);
        return "redirect:/detento/listar";
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
    public String editarFuncionarios(@PathVariable("id") UUID id, DetentoModel editDetentoModel ) {

        detentoService.editar(editDetentoModel);
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/obter/{id}")
    public String obterFuncionario(@PathVariable("id") UUID id, Model model) {
        Optional<DetentoModel> detento = detentoService.obterDetentoModel(id);
        model.addAttribute("detento", detento.orElse(new DetentoModel()));  // Usando orElse para evitar null
        return "detentos/editar-detento";
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarFuncionario(@PathVariable("id") UUID id) {
        DetentoModel detento = detentoService.eliminar(id);

        if (detento != null) {
            return ResponseEntity.ok("Detento excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
