package com.bluehouse.bluehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bluehouse.bluehouse.services.FuncionarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        
        private final FuncionarioService funcionarioService;

    public SecurityConfig(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form->form
                        .loginPage("/autenticacao/login").permitAll()
                        .failureUrl("/autenticacao/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("senha")
                        )
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/").hasAnyAuthority("admin", "user")
                                .requestMatchers("/funcionarios/**").hasAnyAuthority("admin")
                                .requestMatchers("/medidasDisciplinares/**").hasAnyAuthority("admin")
                                .anyRequest().authenticated())
                                .userDetailsService(funcionarioService)
                                .build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
