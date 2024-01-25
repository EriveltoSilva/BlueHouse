package com.bluehouse.bluehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.services.FuncionarioService;

@SpringBootApplication
public class TerminalAPP implements CommandLineRunner {
     @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(TerminalAPP.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Vamos realizar um teste simples de autenticação
        testAuthentication("rita@gmail.com", "rita123");

        // Adicione mais testes conforme necessário
    }

    private void testAuthentication(String email, String senha) {
        try {
            System.out.println("usuário: " + email);
            System.out.println("senha:  " + senha);
            FuncionarioModel user = (FuncionarioModel) funcionarioService.loadUserByUsername(email);

            var senha1 = passwordEncoder.matches(senha, user.getPassword());
            if (user != null && senha1) {
                System.out.println("Autenticação bem-sucedida para o usuário: " + email + " Senha " + senha1 );
            } else if(user.equals(null)) {
            System.out.println("usuário: null " + email + "Senha" + senha1 );
            }
            else if( user.getPassword().equals(null)) {
                System.out.println("usuário: null " + email);
            }
            else {
                System.out.println("Falha na autenticação para o usuário: " + email);
            }
        } catch (Exception e) {
            System.err.println("Erro durante a autenticação: " + e.getMessage());
        }
    }
}
