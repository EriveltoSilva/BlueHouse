package com.bluehouse.bluehouse.controllers.ocorrencias;

import com.bluehouse.bluehouse.DTO.FormularioAutoNoticiaDTO;
import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import com.bluehouse.bluehouse.services.ocorrencias.AutoNoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AutoNoticiaController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private AutoNoticiaService autoNoticiaService;

    @GetMapping("/ocorrencias/auto-noticia/cadastrar")
    public String cadastrarOcorrencia(Model model) {
        FormularioAutoNoticiaDTO form = new FormularioAutoNoticiaDTO();
        form.setTipoOcorrencia("AUTO DE NOTICIA");
        model.addAttribute("formulario", form);
        return "ocorrencias/auto-noticia/cadastrar-auto-noticia";
    }

    @GetMapping("/ocorrencias/auto-noticia/editar/{id}")
    public String editarAutoNoticiaForm(@PathVariable("id") UUID id, Model model) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(id);
        FormularioAutoNoticiaDTO form = new FormularioAutoNoticiaDTO();
        if (autoNoticiaOptional.isPresent()) {
            AutoNoticiaModel autoNoticiaModel = autoNoticiaOptional.get();
            form.setId(autoNoticiaModel.getId());
            form.setFuncionario(autoNoticiaModel.getFuncionario());
            form.setIdFuncionario(autoNoticiaModel.getFuncionario().getId());

            // // Configurar os atributos do Auto de Noticia
            form.setDataOcorrido(
                    autoNoticiaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setDataReporte(
                    autoNoticiaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setHoraOcorrido(autoNoticiaModel.getHoraOcorrido());
            form.setTipoOcorrencia(autoNoticiaModel.getTipoOcorrencia());
            form.setLocal(autoNoticiaModel.getLocal());
            form.setDescricao(autoNoticiaModel.getDescricao());
            model.addAttribute("formAutoNoticia", form); // Usando orElse para evitar null
        }
        return "ocorrencias/auto-noticia/editar-auto-noticia";
    }

    @GetMapping("ocorrencias/auto-noticia/detalhes/{id}")
    public String detalhesDenuncia(@PathVariable("id") UUID id, Model model) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(id);
        FormularioAutoNoticiaDTO form = new FormularioAutoNoticiaDTO();
        if (autoNoticiaOptional.isPresent()) {
            AutoNoticiaModel autoNoticiaModel = autoNoticiaOptional.get();
            form.setId(autoNoticiaModel.getId());
            form.setFuncionario(autoNoticiaModel.getFuncionario());
            form.setIdFuncionario(autoNoticiaModel.getFuncionario().getId());

            // // Configurar os atributos do Auto de Noticia
            form.setDataOcorrido(
                    autoNoticiaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setDataReporte(
                    autoNoticiaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setHoraOcorrido(autoNoticiaModel.getHoraOcorrido());
            form.setTipoOcorrencia(autoNoticiaModel.getTipoOcorrencia());
            form.setLocal(autoNoticiaModel.getLocal());
            form.setDescricao(autoNoticiaModel.getDescricao());
            model.addAttribute("formAutoNoticia", form);
        }

        // return "redirect:/ocorrencias/listar";
        return "ocorrencias/auto-noticia/detalhes-auto-noticia";
    }

    @PostMapping("/ocorrencias/auto-noticia/cadastrar")
    public String processarFormulario(@ModelAttribute("formulario") FormularioAutoNoticiaDTO formulario) {
        FuncionarioModel funcionario;
        if (formulario.getIdFuncionario() == null)
            return "redirect:/";
        else {
            Optional<FuncionarioModel> funcionarioOptional = funcionarioService
                    .obterFuncionarioModel(formulario.getIdFuncionario());
            if (!funcionarioOptional.isPresent())
                return "redirect:/";
            funcionario = funcionarioOptional.get();
        }

        AutoNoticiaModel autoNoticia = new AutoNoticiaModel();
        autoNoticia.setDataReporte(Date.valueOf(formulario.getDataReporte()));
        autoNoticia.setDataOcorrido(Date.valueOf(formulario.getDataOcorrido()));
        autoNoticia.setHoraOcorrido(formulario.getHoraOcorrido());
        autoNoticia.setLocal(formulario.getLocal());
        autoNoticia.setDescricao(formulario.getDescricao());
        autoNoticia.setTipoOcorrencia(formulario.getTipoOcorrencia());

        // Relacionamento entre Pessoa e Denuncia (1:n)
        autoNoticia.setFuncionario(funcionario);
        funcionario.getAutoNoticias().add(autoNoticia);

        //funcionarioService.editar(funcionario);
        autoNoticiaService.criar(autoNoticia);
        return "redirect:/ocorrencias/listar";
    }

    @PostMapping("/ocorrencias/auto-noticia/editar")
    public String editarDenuncia(@ModelAttribute("formAutoNoticia") FormularioAutoNoticiaDTO formAutoNoticia) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(formAutoNoticia.getId());
        if (!autoNoticiaOptional.isPresent())
            return "redirect:/";

        AutoNoticiaModel autoNoticia = autoNoticiaOptional.get();
        autoNoticia.setDataReporte(Date.valueOf(formAutoNoticia.getDataReporte()));
        autoNoticia.setDataOcorrido(Date.valueOf(formAutoNoticia.getDataOcorrido()));
        autoNoticia.setHoraOcorrido(formAutoNoticia.getHoraOcorrido());
        autoNoticia.setLocal(formAutoNoticia.getLocal());
        autoNoticia.setDescricao(formAutoNoticia.getDescricao());
        autoNoticiaService.editar(autoNoticia);
        return "redirect:/ocorrencias/listar";
    }


    @GetMapping("/ocorrencias/auto-noticia/eliminar/{id}")
    public String eliminarAutoNoticia(@PathVariable("id") UUID id, Model model) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(id);
        if (autoNoticiaOptional.isPresent()) 
            autoNoticiaService.eliminar(id);
        else
            return "redirect:/";
        return "redirect:/ocorrencias/listar";
    }
}
