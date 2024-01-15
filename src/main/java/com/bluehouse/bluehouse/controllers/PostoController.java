package com.bluehouse.bluehouse.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluehouse.bluehouse.models.PostoModel;
import com.bluehouse.bluehouse.services.PostoService;

@Controller
@RequestMapping("/postos")
public class PostoController {

    @Autowired
    private PostoService postoService;

    /* Rotas Simples - Retorno de Páginas*/
    @GetMapping("/cadastrar")
    public String formCadastrarPosto() {
        return "postos/cadastrar-posto";
    }
    @PostMapping("/cadastrar")
    public String cadastrarPosto(PostoModel posto) {
        postoService.criar(posto);
        return "redirect:/postos/listar";
    }

    @GetMapping("/listar")
    public String listarPostos(Model model) {
        List<PostoModel> postos = postoService.listar();
        model.addAttribute("postos", postos);
        return "postos/listar-postos";
    } 

    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable("id") UUID id, Model model) {
        Optional<PostoModel> posto = postoService.obtePostoModel(id);
        
        if (posto.isPresent()) {
            model.addAttribute("funcionario", posto.get());
            return "funcionarios/editar-funcionario";
        } else {
            // Tratar o caso em que o funcionário não foi encontrado
            return "redirect:/funcionarios/listar";
        }
    }

    @PostMapping("/editar/{id}")
    public String editarFuncionarios(@PathVariable("id") UUID id, PostoModel editPostoModel ) {

        postoService.editar(editPostoModel);
        return "redirect:/postos/listar";
    }

    @GetMapping("/obter/{id}")
    public String obterPosto(@PathVariable("id") UUID id, Model model) {
        Optional<PostoModel> funcionario = postoService.obtePostoModel(id);
        model.addAttribute("funcionario", funcionario.orElse(new PostoModel()));
        return "funcionarios/editar-posto";
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarFuncionario(@PathVariable("id") UUID id) {
        PostoModel posto = postoService.eliminar(id);

        if (posto != null) {
            return ResponseEntity.ok("Funcionário excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }     
}
