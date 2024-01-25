package com.bluehouse.bluehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutenticacaoController {

    @GetMapping("/autenticacao/login")
    public String formLogin(){
        return "autenticacao/login";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
