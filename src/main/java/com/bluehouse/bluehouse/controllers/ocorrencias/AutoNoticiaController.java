package com.bluehouse.bluehouse.controllers.ocorrencias;

import com.bluehouse.bluehouse.DTO.FormularioAutoNoticiaDTO;
import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import com.bluehouse.bluehouse.services.ocorrencias.AutoNoticiaService;

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
    public String editarAutoNoticiaForm(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
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
            form.setEstado(autoNoticiaModel.getEstado());
            model.addAttribute("formAutoNoticia", form); // Usando orElse para evitar null
        }
        return "ocorrencias/auto-noticia/editar-auto-noticia";
    }

    @GetMapping("/ocorrencias/auto-noticia/detalhes/{id}")
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
            form.setEstado(autoNoticiaModel.getEstado());
            model.addAttribute("formAutoNoticia", form);
        }

        // return "redirect:/ocorrencias/listar";
        return "ocorrencias/auto-noticia/detalhes-auto-noticia";
    }

    @PostMapping("/ocorrencias/auto-noticia/cadastrar")
    public String processarFormulario(@ModelAttribute("formulario") FormularioAutoNoticiaDTO formulario, RedirectAttributes redirectAttributes) {
        FuncionarioModel funcionario;
        if (formulario.getIdFuncionario() == null)
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este funcionario não existe");
            return "redirect:/";
        }
        else {
            Optional<FuncionarioModel> funcionarioOptional = funcionarioService
                    .obterFuncionarioModel(formulario.getIdFuncionario());
            if (!funcionarioOptional.isPresent())
            {
                redirectAttributes.addFlashAttribute("mensagemErro", "Este funcionario não existe");
                return "redirect:/";
            }
            funcionario = funcionarioOptional.get();
        }

        AutoNoticiaModel autoNoticia = new AutoNoticiaModel();
        autoNoticia.setDataReporte(Date.valueOf(formulario.getDataReporte()));
        autoNoticia.setDataOcorrido(Date.valueOf(formulario.getDataOcorrido()));
        autoNoticia.setHoraOcorrido(formulario.getHoraOcorrido());
        autoNoticia.setLocal(formulario.getLocal());
        autoNoticia.setDescricao(formulario.getDescricao());
        autoNoticia.setTipoOcorrencia(formulario.getTipoOcorrencia());
        autoNoticia.setEstado("Activo");
        // Relacionamento entre Pessoa e Denuncia (1:n)
        autoNoticia.setFuncionario(funcionario);
        funcionario.getAutoNoticias().add(autoNoticia);

        //funcionarioService.editar(funcionario);
        autoNoticiaService.criar(autoNoticia);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Auto de Noticia registrada com sucesso");

        return "redirect:/ocorrencias/listar";
    }

    @PostMapping("/ocorrencias/auto-noticia/editar")
    public String editarDenuncia(@ModelAttribute("formAutoNoticia") FormularioAutoNoticiaDTO formAutoNoticia, RedirectAttributes redirectAttributes) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(formAutoNoticia.getId());
        if (!autoNoticiaOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Auto de Noticia não encontrada");
            return "redirect:/";
        }

        AutoNoticiaModel autoNoticia = autoNoticiaOptional.get();
        autoNoticia.setDataReporte(Date.valueOf(formAutoNoticia.getDataReporte()));
        autoNoticia.setDataOcorrido(Date.valueOf(formAutoNoticia.getDataOcorrido()));
        autoNoticia.setHoraOcorrido(formAutoNoticia.getHoraOcorrido());
        autoNoticia.setLocal(formAutoNoticia.getLocal());
        autoNoticia.setDescricao(formAutoNoticia.getDescricao());
        autoNoticia.setEstado(formAutoNoticia.getEstado());
        autoNoticiaService.editar(autoNoticia);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Auto de Noticia editada com sucesso");

        return "redirect:/ocorrencias/listar";
    }


    @GetMapping("/ocorrencias/auto-noticia/eliminar/{id}")
    public String eliminarAutoNoticia(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<AutoNoticiaModel> autoNoticiaOptional = autoNoticiaService.obterAutoNoticiaModel(id);
        if (autoNoticiaOptional.isPresent())
        {
            autoNoticiaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Auto de Noticia registrada com sucesso");
        } 
        else{
            redirectAttributes.addFlashAttribute("mensagemErro", "Auto de Noticia não existe");
            return "redirect:/";
        }
        return "redirect:/ocorrencias/listar";
    }

    @GetMapping("/ocorrencias/auto-noticia/pesquisar")
    public String pesquisarOcorrencias(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<AutoNoticiaModel> resultadosAutoNoticias;
        if (keyword != null) {
            resultadosAutoNoticias = autoNoticiaService.pesquisarPorNomeOuEstado(keyword);
        } else {
            resultadosAutoNoticias = new ArrayList<>(); 
        }
        model.addAttribute("resultadosAutoNoticias", resultadosAutoNoticias);
        return "ocorrencias/auto-noticia/listar-pesquisas";
    }

    @GetMapping("/ocorrencias/auto-noticia/relatorio")
    public String gerarRelatorioPDF(RedirectAttributes redirectAttributes) throws IOException {
        // Crie um novo documento PDF
        PDDocument document = new PDDocument();

        // Adicione uma página ao documento
        PDPage page = new PDPage();
        document.addPage(page);

        List<AutoNoticiaModel> autoNoticias = autoNoticiaService.listar();

        // Crie um stream de conteúdo para escrever no PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adicione o conteúdo do relatório (você precisa personalizar isso de acordo com sua necessidade)
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.showText("Relatório de Auto de Noticias");
        contentStream.newLineAtOffset(0, -20);

        int yPosition = 700;
        int lineHeight = 12;
        for (AutoNoticiaModel autoNoticia : autoNoticias) {
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
            contentStream.showText("ID: " + autoNoticia.getId());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Nome do Funcionario: " + autoNoticia.getFuncionario().getNomeCompleto());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Descrição: " + autoNoticia.getDescricao());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Hora do Ocorrido: " + autoNoticia.getHoraOcorrido());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data do Reporte: " + autoNoticia.getDataReporte());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Data do Ocorrido: " + autoNoticia.getDataOcorrido());

            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Local: " + autoNoticia.getLocal());

        }

        contentStream.endText();
        contentStream.close();

        document.save("relatorio_autoNoticias.pdf");
        document.close();

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Relatório (Auto de Noticias) gerado com sucesso");
        return "redirect:/ocorrencias/listar";
    }
}
