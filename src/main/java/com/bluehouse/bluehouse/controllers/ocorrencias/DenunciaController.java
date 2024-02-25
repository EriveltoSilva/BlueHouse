package com.bluehouse.bluehouse.controllers.ocorrencias;

import com.bluehouse.bluehouse.DTO.FormularioDenunciaDTO;
import com.bluehouse.bluehouse.models.ReportanteModel;
import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import com.bluehouse.bluehouse.services.ReportanteService;
import com.bluehouse.bluehouse.services.ocorrencias.DenunciaService;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;


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
        model.addAttribute("formulario", form);
        return "ocorrencias/denuncia/cadastrar-denuncia";
    }


    @GetMapping("/ocorrencias/denuncia/editar/{id}")
    public String editarDenunciaForm(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<DenunciaModel> denunciaOptional = denunciaService.obterDenunciaModel(id);
        FormularioDenunciaDTO form = new FormularioDenunciaDTO();
        if (denunciaOptional.isPresent()) {
            DenunciaModel denunciaModel = denunciaOptional.get();
            form.setId(denunciaModel.getId());
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
            form.setEstado(denunciaModel.getEstado());
            model.addAttribute("formDenuncia", form); // Usando orElse para evitar null
        }
        return "ocorrencias/denuncia/editar-denuncia";
    }

    @GetMapping("ocorrencias/denuncia/detalhes/{id}")
    public String detalhesDenuncia(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        
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
            form.setEstado(denunciaModel.getEstado());
            model.addAttribute("formDenuncia", form); // Usando orElse para evitar null
        }

        // return "redirect:/ocorrencias/listar";
        return "ocorrencias/denuncia/detalhes-denuncia";
    }

    @PostMapping("/ocorrencias/denuncia/cadastrar")
    public String processarFormulario(@ModelAttribute("formulario") FormularioDenunciaDTO formulario, RedirectAttributes redirectAttributes) {
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
        denuncia.setEstado("Activo");
        denuncia.setReportante(reportante);
        reportante.getDenuncias().add(denuncia);

        reportanteService.criar(reportante);
        denunciaService.criar(denuncia);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Denúncia registrada com sucesso");

        return "redirect:/ocorrencias/listar";
    }

    @PostMapping("/ocorrencias/denuncia/editar")
    public String editarDenuncia(@ModelAttribute("formDenuncia") FormularioDenunciaDTO formDenuncia, RedirectAttributes redirectAttributes) {
        Optional<DenunciaModel> denunciaOptional = denunciaService.obterDenunciaModel(formDenuncia.getId());
        if(!denunciaOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este denuncia não existe");
            return "redirect:/";
        }

        DenunciaModel denuncia  = denunciaOptional.get();
        denuncia.setDataDenuncia(Date.valueOf(formDenuncia.getDataDenuncia()));
        denuncia.setDataOcorrido(Date.valueOf(formDenuncia.getDataOcorrido()));
        denuncia.setHoraOcorrido(formDenuncia.getHoraOcorrido());
        denuncia.setDescricao(formDenuncia.getDescricao());
        denuncia.setEstado(formDenuncia.getEstado());
        denunciaService.editar(denuncia);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Denúncia editada com sucesso");

        return "redirect:/ocorrencias/listar";
    }

    @GetMapping("/ocorrencias/denuncia/pesquisar")
    public String pesquisarOcorrencias(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<DenunciaModel> resultadosDenuncias;
        if (keyword != null) {
            resultadosDenuncias = denunciaService.pesquisarPorNomeOuEstado(keyword);
        } else {
            resultadosDenuncias = new ArrayList<>(); 
        }
        model.addAttribute("resultadosDenuncias", resultadosDenuncias);
        return "ocorrencias/denuncia/listar-pesquisas";
    }
    
    
    @GetMapping("/ocorrencias/denuncia/relatorio")
    public String gerarRelatorioPDF(RedirectAttributes redirectAttributes) throws IOException {
        // Crie um novo documento PDF
        PDDocument document = new PDDocument();

        // Adicione uma página ao documento
        PDPage page = new PDPage();
        document.addPage(page);

        List<DenunciaModel> denuncias = denunciaService.listar();

        // Crie um stream de conteúdo para escrever no PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adicione o conteúdo do relatório (você precisa personalizar isso de acordo com sua necessidade)
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.showText("Relatório de Denúncias");
        contentStream.newLineAtOffset(0, -20);

        int yPosition = 700; // Defina a posição Y inicial
        int lineHeight = 12; // Altura da linha de texto
        for (DenunciaModel denuncia : denuncias) {
            yPosition -= lineHeight; // Ajuste o espaçamento conforme necessário

            // Verifique se há espaço suficiente para a próxima linha
            if (yPosition < 20) {
                // Se não houver espaço suficiente, crie uma nova página
                contentStream.endText();
                contentStream.close();

                page = new PDPage();
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                
                // Reinicie o texto na nova página
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                yPosition = 700; // Defina a nova posição Y inicial
            }

            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("ID: " + denuncia.getId());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Nome do Reportante: " + denuncia.getReportante().getNomeCompleto());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Descrição: " + denuncia.getDescricao());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Hora do Ocorrido: " + denuncia.getHoraOcorrido());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data da Denúncia: " + denuncia.getDataDenuncia());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data do Ocorrido: " + denuncia.getDataOcorrido());
        }

        contentStream.endText();
        contentStream.close();

        document.save("relatorio_denuncias.pdf");
        document.close();

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Relatório denuncia gerado com sucesso");
        return "redirect:/ocorrencias/listar";
    }
}
