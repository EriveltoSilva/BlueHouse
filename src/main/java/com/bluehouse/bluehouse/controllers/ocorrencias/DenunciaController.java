package com.bluehouse.bluehouse.controllers.ocorrencias;

import com.bluehouse.bluehouse.DTO.FormularioDenunciaDTO;
import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.ReportanteModel;
import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import com.bluehouse.bluehouse.services.ReportanteService;
import com.bluehouse.bluehouse.services.ocorrencias.DenunciaService;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DenunciaController {
    @Autowired
    private ReportanteService reportanteService;
    @Autowired
    private DenunciaService denunciaService;

    @GetMapping("/ocorrencias/denuncia/cadastrar")
    public String cadastrarOcorrencia(Model model) {
        FormularioDenunciaDTO form = new FormularioDenunciaDTO();
        form.setTipoOcorrencia("DENUNCIA");
        // form.setNomeCompleto("Erivelto Silva");
        model.addAttribute("formulario", form);
        return "ocorrencias/denuncia/cadastrar-denuncia";
    }

    @GetMapping("ocorrencias/denuncia/detalhes/{id}")
    public String detalhesDenuncia(@PathVariable("id") UUID id, Model model) {
        System.out.println("Entrei");
        Optional<DenunciaModel> denunciaOptional = denunciaService.obterDenunciaModel(id);
        FormularioDenunciaDTO form = new FormularioDenunciaDTO();
        if (denunciaOptional.isPresent()) {
            DenunciaModel denunciaModel = denunciaOptional.get();
            form.setNomeCompleto(denunciaModel.getReportante().getNomeCompleto());
            form.setDataNascimento(denunciaModel.getReportante().getDataNascimento().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate());
            form.setBi(denunciaModel.getReportante().getBi());
            form.setEndereco(denunciaModel.getReportante().getEndereco());
            form.setContacto(denunciaModel.getReportante().getContacto());
            form.setGenero(denunciaModel.getReportante().getGenero());

            // // Configurar os atributos da Denúncia
            form.setDataDenuncia(
                    denunciaModel.getDataDenuncia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setDataOcorrido(
                    denunciaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setHoraOcorrido(denunciaModel.getHoraOcorrido());
            form.setTipoOcorrencia(denunciaModel.getTipoOcorrencia());
            form.setDescricao(denunciaModel.getDescricao());
            model.addAttribute("formDenuncia", form); // Usando orElse para evitar null
        }

        // return "redirect:/ocorrencias/listar";
        return "ocorrencias/denuncia/detalhes-denuncia";
    }

    @PostMapping("/ocorrencias/denuncia/cadastrar")
    public String processarFormulario(@ModelAttribute("formulario") FormularioDenunciaDTO formulario) {
        ReportanteModel reportante;
        if (formulario.getId() == null) {
            reportante = new ReportanteModel();
            reportante.setNomeCompleto(formulario.getNomeCompleto());
            reportante.setBi(formulario.getBi());
            reportante.setContacto(formulario.getContacto());
            reportante.setDataNascimento(Date.valueOf(formulario.getDataNascimento()));
            reportante.setEndereco(formulario.getEndereco());
            reportante.setGenero(formulario.getGenero());
        } else {
            Optional<ReportanteModel> reportanteOptional = reportanteService.obterReportanteModel(formulario.getId());
            reportante = (reportanteOptional.isPresent()) ? reportanteOptional.get() : new ReportanteModel();
        }

        DenunciaModel denuncia = new DenunciaModel();
        denuncia.setDataDenuncia(Date.valueOf(formulario.getDataDenuncia()));
        denuncia.setDataOcorrido(Date.valueOf(formulario.getDataOcorrido()));
        denuncia.setHoraOcorrido(formulario.getHoraOcorrido());
        denuncia.setDescricao(formulario.getDescricao());
        denuncia.setTipoOcorrencia(formulario.getTipoOcorrencia());
        // Relacionamento entre Pessoa e Denuncia (1:n)
        denuncia.setReportante(reportante);
        reportante.getDenuncias().add(denuncia);

        reportanteService.criar(reportante);
        denunciaService.criar(denuncia);
        return "redirect:/ocorrencias/listar";
    }
}
