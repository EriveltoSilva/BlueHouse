package com.bluehouse.bluehouse.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;

@Controller
public class EditarUsuarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuarioForm(@PathVariable("id") UUID id, Model model) {
        Optional<FuncionarioModel> funcionario = funcionarioService.obterFuncionarioModel(id);

        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "usuario/editar-usuario";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") UUID id, @ModelAttribute("funcionario") FuncionarioModel editFuncionarioModel) {
        Optional<FuncionarioModel> usuarioOptional = funcionarioService.obterFuncionarioModel(id);
    
        if (usuarioOptional.isPresent()) {
            FuncionarioModel usuario = usuarioOptional.get();
            usuario.setNomeCompleto(editFuncionarioModel.getNomeCompleto());
            usuario.setDataNascimento(editFuncionarioModel.getDataNascimento());
            usuario.setBi(editFuncionarioModel.getBi());
            usuario.setEndereco(editFuncionarioModel.getEndereco());
            usuario.setContacto(editFuncionarioModel.getContacto());
            usuario.setGenero(editFuncionarioModel.getGenero());
            String newPassword = editFuncionarioModel.getSenha();
            if (newPassword != null && !newPassword.isEmpty()) {
                usuario.setPassword(newPassword);
            }

            funcionarioService.editar(usuario);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
    
}