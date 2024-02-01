package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {
    @GetMapping("/relatorios/dashboard")
    public String escreverRelatorio() {
        return "relatorios/dashboard";
    }

}
