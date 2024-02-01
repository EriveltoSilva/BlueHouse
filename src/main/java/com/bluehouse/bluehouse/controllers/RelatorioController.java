package com.bluehouse.bluehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bluehouse.bluehouse.services.DetentoService;
import com.bluehouse.bluehouse.services.FuncionarioService;
import com.bluehouse.bluehouse.services.HorarioService;
import com.bluehouse.bluehouse.services.MedidaDisciplinarService;
import com.bluehouse.bluehouse.services.PostoService;
import com.bluehouse.bluehouse.services.TurnoService;
import com.bluehouse.bluehouse.services.ocorrencias.AutoNoticiaService;
import com.bluehouse.bluehouse.services.ocorrencias.DenunciaService;
import com.bluehouse.bluehouse.services.ocorrencias.QueixaService;

@Controller
public class RelatorioController {
    @Autowired 
    private FuncionarioService funcionarioService;
    
    @Autowired 
    private MedidaDisciplinarService medidasDisciplinaresService;
    
    @Autowired
    private DetentoService detentoService;

    @Autowired 
    private PostoService postoService;
    
    @Autowired 
    private DenunciaService denunciaService;

    @Autowired 
    private QueixaService queixaService;

    @Autowired 
    private AutoNoticiaService autoNoticiaService;
    
    @Autowired 
    private HorarioService horarioService;

    @Autowired 
    private TurnoService turnoService;

    
    // private MedidaDisciplinarService medidasDisciplinaresService;

    

    @GetMapping("/relatorios/dashboard")
    public String dashboardRelatorio(Model model) {

        
        model.addAttribute("totalFuncionarios",(long) funcionarioService.getTotal());
        model.addAttribute("totalMedidasDispiplinares",(long) medidasDisciplinaresService.getTotal());
        
        model.addAttribute("totalDetentos",(long) detentoService.getTotal());
        model.addAttribute("totalPostos",(long) postoService.getTotal());
        
        model.addAttribute("totalDenuncias",(long) denunciaService.getTotal());
        model.addAttribute("totalQueixas",(long) queixaService.getTotal());
        
        model.addAttribute("totalAutoNoticias",(long) autoNoticiaService.getTotal());
        model.addAttribute("totalHorarios",(long) horarioService.getTotal());
        model.addAttribute("totalTurnos",(long) turnoService.getTotal());

        model.addAttribute("funcionarios", funcionarioService.listar());
        return "relatorios/dashboard";
    }

}
