package com.bluehouse.bluehouse.controllers.ocorrencias;

import com.bluehouse.bluehouse.DTO.FormularioQueixaDTO;
import com.bluehouse.bluehouse.models.ReportanteModel;
import com.bluehouse.bluehouse.models.ocorrencias.QueixaModel;
import com.bluehouse.bluehouse.services.ReportanteService;
import com.bluehouse.bluehouse.services.ocorrencias.QueixaService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class QueixaController {
    @Autowired
    private ReportanteService reportanteService;
    @Autowired
    private QueixaService queixaService;

    @GetMapping("/ocorrencias/queixa/cadastrar")
    public String cadastrarOcorrencia(Model model) {
        FormularioQueixaDTO form = new FormularioQueixaDTO();
        form.setTipoOcorrencia("QUEIXA");
        model.addAttribute("formulario", form);
        return "ocorrencias/queixa/cadastrar-queixa";
    }


    @GetMapping("/ocorrencias/queixa/editar/{id}")
    public String editarQuexiaForm(@PathVariable("id") UUID id, Model model) {
        Optional<QueixaModel> queixaOptional = queixaService.obterQueixaModel(id);
        FormularioQueixaDTO form = new FormularioQueixaDTO();
        if (queixaOptional.isPresent()) {
            QueixaModel queixaModel = queixaOptional.get();
            form.setId(queixaModel.getId());
            form.setNomeCompleto(queixaModel.getReportante().getNomeCompleto());
            form.setDataNascimento(queixaModel.getReportante().getDataNascimento().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate());
            form.setBi(queixaModel.getReportante().getBi());
            form.setEndereco(queixaModel.getReportante().getEndereco());
            form.setContacto(queixaModel.getReportante().getContacto());
            form.setGenero(queixaModel.getReportante().getGenero());

            // // Configurar os atributos da Denúncia
            form.setDataQueixa(
                    queixaModel.getDataQueixa().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setDataOcorrido(
                    queixaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setHoraOcorrido(queixaModel.getHoraOcorrido());
            form.setTipoOcorrencia(queixaModel.getTipoOcorrencia());
            form.setDescricao(queixaModel.getDescricao());
            form.setEstado(queixaModel.getEstado());
            model.addAttribute("formQueixa", form); // Usando orElse para evitar null
        }
        return "ocorrencias/queixa/editar-queixa";
    }

    @GetMapping("ocorrencias/queixa/detalhes/{id}")
    public String detalhesQueixa(@PathVariable("id") UUID id, Model model) {System.out.println("Entrei");
        Optional<QueixaModel> queixaOptional = queixaService.obterQueixaModel(id);
        FormularioQueixaDTO form = new FormularioQueixaDTO();
        if (queixaOptional.isPresent()) {
            QueixaModel queixaModel = queixaOptional.get();
            form.setNomeCompleto(queixaModel.getReportante().getNomeCompleto());
            form.setDataNascimento(queixaModel.getReportante().getDataNascimento().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate());
            form.setBi(queixaModel.getReportante().getBi());
            form.setEndereco(queixaModel.getReportante().getEndereco());
            form.setContacto(queixaModel.getReportante().getContacto());
            form.setGenero(queixaModel.getReportante().getGenero());

            // // Configurar os atributos da Denúncia
            form.setDataQueixa(
                    queixaModel.getDataQueixa().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setDataOcorrido(
                    queixaModel.getDataOcorrido().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            form.setHoraOcorrido(queixaModel.getHoraOcorrido());
            form.setTipoOcorrencia(queixaModel.getTipoOcorrencia());
            form.setDescricao(queixaModel.getDescricao());
            form.setEstado(queixaModel.getEstado());
            model.addAttribute("formQueixa", form); // Usando orElse para evitar null
        }

        // return "redirect:/ocorrencias/listar";
        return "ocorrencias/queixa/detalhes-queixa";
    }

    @PostMapping("/ocorrencias/queixa/cadastrar")
    public String processarFormulario(@ModelAttribute("formulario") FormularioQueixaDTO formulario, RedirectAttributes redirectAttributes) {
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

        QueixaModel queixa = new QueixaModel();
        queixa.setDataQueixa(Date.valueOf(formulario.getDataQueixa()));
        queixa.setDataOcorrido(Date.valueOf(formulario.getDataOcorrido()));
        queixa.setHoraOcorrido(formulario.getHoraOcorrido());
        queixa.setDescricao(formulario.getDescricao());
        queixa.setTipoOcorrencia(formulario.getTipoOcorrencia());
        queixa.setEstado("Activo");
        // Relacionamento entre Pessoa e Queixa (1:n)
        queixa.setReportante(reportante);
        reportante.getQueixas().add(queixa);

        reportanteService.criar(reportante);
        queixaService.criar(queixa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Queixa registrada com sucesso");

        return "redirect:/ocorrencias/listar";
    }

    @PostMapping("/ocorrencias/queixa/editar")
    public String editarQueixa(@ModelAttribute("formQueixa") FormularioQueixaDTO formQueixa, RedirectAttributes redirectAttributes) {
        Optional<QueixaModel> queixaOptional = queixaService.obterQueixaModel(formQueixa.getId());
        if(!queixaOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Esta queixa não existe");
            return "redirect:/";
        }

        QueixaModel queixa  = queixaOptional.get();
        queixa.setDataQueixa(Date.valueOf(formQueixa.getDataQueixa()));
        queixa.setDataOcorrido(Date.valueOf(formQueixa.getDataOcorrido()));
        queixa.setHoraOcorrido(formQueixa.getHoraOcorrido());
        queixa.setDescricao(formQueixa.getDescricao());
        queixa.setEstado(formQueixa.getEstado());
        queixaService.editar(queixa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Queixa editada com sucesso");

        return "redirect:/ocorrencias/listar";
    }

    @GetMapping("/ocorrencias/queixa/pesquisar")
    public String pesquisarOcorrencias(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<QueixaModel> resultadosQueixas;
        if (keyword != null) {
            resultadosQueixas = queixaService.pesquisarPorNomeOuEstado(keyword);
        } else {
            resultadosQueixas = new ArrayList<>(); 
        }
        model.addAttribute("resultadosQueixas", resultadosQueixas);
        return "ocorrencias/queixa/listar-pesquisas";
    }

    @GetMapping("/ocorrencias/queixa/relatorio")
    public String gerarRelatorioPDF(RedirectAttributes redirectAttributes) throws IOException {
        // Crie um novo documento PDF
        PDDocument document = new PDDocument();

        // Adicione uma página ao documento
        PDPage page = new PDPage();
        document.addPage(page);

        List<QueixaModel> queixas = queixaService.listar();

        // Crie um stream de conteúdo para escrever no PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adicione o conteúdo do relatório (você precisa personalizar isso de acordo com sua necessidade)
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.showText("Relatório de Queixas");
        contentStream.newLineAtOffset(0, -20);

        int yPosition = 700;
        int lineHeight = 12;
        for (QueixaModel queixa : queixas) {
            yPosition -= lineHeight;

            if (yPosition < 20) {
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
            contentStream.showText("ID: " + queixa.getId());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Nome do Reportante: " + queixa.getReportante().getNomeCompleto());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Descrição: " + queixa.getDescricao());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Hora do Ocorrido: " + queixa.getHoraOcorrido());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data da Queixa: " + queixa.getDataQueixa());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data do Ocorrido: " + queixa.getDataOcorrido());
        }

        contentStream.endText();
        contentStream.close();

        document.save("relatorio_queixas.pdf");
        document.close();

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Relatório (queixa) gerado com sucesso");
        return "redirect:/ocorrencias/listar";
    }
}
