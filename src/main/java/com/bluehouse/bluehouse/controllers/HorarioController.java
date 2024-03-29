package com.bluehouse.bluehouse.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bluehouse.bluehouse.DTO.FormularioHorarioDTO;
import com.bluehouse.bluehouse.models.HorarioModel;
import com.bluehouse.bluehouse.models.TurnoModel;
import com.bluehouse.bluehouse.services.HorarioService;
import com.bluehouse.bluehouse.services.TurnoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HorarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/horarios/cadastrar")
    public String cadastrarHorario(Model model) {
        List<FuncionarioModel> funcionarios = funcionarioService.listar();
        FormularioHorarioDTO form = new FormularioHorarioDTO();
        form.setFuncionarios(funcionarios);
        model.addAttribute("formulario", form);
        return "horarios/cadastrar-horario";
    }

    @GetMapping("/horarios/eliminar/{id}")
    public String eliminarTurno(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TurnoModel> turnoOptional = turnoService.obterTurnoModel(id);
        if (!turnoOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este turno não existe");
            return "redirect:/";
        }
        turnoService.eliminar(turnoOptional.get().getId());
        redirectAttributes.addFlashAttribute("mensagemSuccsso", "Turno eliminado");
        return "redirect:/horarios/listar";
    }

    @GetMapping("/horarios/editar/{id}")
    public String editarHorario(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TurnoModel> turnoOptional = turnoService.obterTurnoModel(id);
        if (!turnoOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este turno não existe");
            return "redirect:/";
        }
        TurnoModel turno = turnoOptional.get();

        FormularioHorarioDTO formulario= new FormularioHorarioDTO();
        formulario.setFuncionarios(funcionarioService.listar());
        formulario.setArea(turno.getArea());
        formulario.setDataTurno(turno.getDataTurno().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        formulario.setHoraFim(turno.getHoraFim());
        formulario.setHoraInicio(turno.getHoraInicio());
        formulario.setIdFuncionario(turno.getFuncionario().getId());
        formulario.setIdHorario(turno.getHorario().getId());
        formulario.setIdTurno(turno.getId());

        model.addAttribute("formulario", formulario);
        return "/horarios/editar-turno";
    }

    @PostMapping("/horarios/editar")
    public String processarEdicaoDeHorario(@Valid @ModelAttribute("formulario") FormularioHorarioDTO formulario, RedirectAttributes redirectAttributes) {
        Optional<FuncionarioModel> funcionariOptional = funcionarioService
                .obterFuncionarioModel(formulario.getIdFuncionario());
        if (!funcionariOptional.isPresent())
            {
                redirectAttributes.addFlashAttribute("mensagemErro", "Este funcionario não existe");
                return "redirect:/";
            }
        FuncionarioModel funcionario = funcionariOptional.get();

        List<HorarioModel> possiveisHorarios = horarioService.obterHorariosParaOMesEmCurso(
                formulario.getDataTurno().getYear(), formulario.getDataTurno().getMonthValue());
        HorarioModel horario;
        if (!possiveisHorarios.isEmpty())
            horario = possiveisHorarios.get(0);
        else {
            horario = new HorarioModel();
            horario.setDataTurno(Date.valueOf(formulario.getDataTurno()));
        }

        TurnoModel turno;
        if (horario.getId()==null) 
            turno = new TurnoModel();
        else{
            Optional<TurnoModel> turnoOptional = turnoService.obterTurnoModel(formulario.getIdTurno());
            if (!turnoOptional.isPresent())
            {
                redirectAttributes.addFlashAttribute("mensagemErro", "Este turno não existe");
                return "redirect:/";
            }
            turno = turnoOptional.get();
        }
            
        turno.setDataTurno(Date.valueOf(formulario.getDataTurno()));
        turno.setArea(formulario.getArea());
        turno.setFuncionario(funcionario);
        turno.setHoraInicio(formulario.getHoraInicio());
        turno.setHoraFim(formulario.getHoraFim());
        turno.setHorario(horario);
        horario.getTurnos().add(turno);
        horarioService.editar(horario);
        turnoService.editar(turno);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Edição registrada com sucesso");
        
        
        return "redirect:/horarios/listar";
    }


    @PostMapping("/horarios/cadastrar")
    public String processarCadastroHorario(@ModelAttribute("formulario") FormularioHorarioDTO formulario, BindingResult bResult, Model model, RedirectAttributes redirectAttributes) 
    {
        if (bResult.hasErrors()) {
            model.addAttribute("formulario", formulario);
            return "horarios/cadastrar-horario";
        }
        Optional<FuncionarioModel> funcionariOptional = funcionarioService
                .obterFuncionarioModel(formulario.getIdFuncionario());
        if (!funcionariOptional.isPresent())
        {
            redirectAttributes.addFlashAttribute("mensagemErro", "Este funcionario não existe");
            return "redirect:/";
        }
        FuncionarioModel funcionario = funcionariOptional.get();

        List<HorarioModel> possiveisHorarios = horarioService.obterHorariosParaOMesEmCurso(
                formulario.getDataTurno().getYear(), formulario.getDataTurno().getMonthValue());
        HorarioModel horario;
        if (!possiveisHorarios.isEmpty())
            horario = possiveisHorarios.get(0);
        else {
            horario = new HorarioModel();
            horario.setDataTurno(Date.valueOf(formulario.getDataTurno()));
        }
        TurnoModel turno = new TurnoModel();
        turno.setDataTurno(Date.valueOf(formulario.getDataTurno()));
        turno.setArea(formulario.getArea());
        turno.setFuncionario(funcionario);
        turno.setHoraInicio(formulario.getHoraInicio());
        turno.setHoraFim(formulario.getHoraFim());

        turno.setHorario(horario);
        horario.getTurnos().add(turno);

        horarioService.criar(horario);
        turnoService.criar(turno);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Turno registrado com sucesso");
        return "redirect:/horarios/listar";
    }
    

    @GetMapping("/horarios/listar")
    public String listarHorariosPorDia(Model model) {
        LocalDate hoje = LocalDate.now();
        List<TurnoModel> turnos = turnoService.obterTurnosOrdenadosPorMesEmCurso(hoje.getYear(), hoje.getMonthValue());
        model.addAttribute("turnos", turnos);
        return "horarios/listar-horarios";
    }

    @GetMapping("/horarios/pesquisar")
    public String pesquisarTurnos(@RequestParam(value = "keyword", required = false) String keyword,
                                        Model model) {
        List<TurnoModel> resultadosTurnos;
        if (keyword != null) {
            resultadosTurnos = turnoService.pesquisarPorNome(keyword);
        } else {
            resultadosTurnos = new ArrayList<>(); 
        }
        model.addAttribute("resultadosTurnos", resultadosTurnos);
        return "horarios/listar-pesquisas";
    }
}
