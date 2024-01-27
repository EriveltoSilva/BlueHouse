package com.bluehouse.bluehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;

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
                                .requestMatchers("/ocorrencias/**").hasAnyAuthority("admin", "user")
                                .requestMatchers("/postos/**").hasAnyAuthority("admin", "user")
                                .requestMatchers("/horarios/**").hasAnyAuthority("admin", "user")
                                .requestMatchers("/detentos/**").hasAnyAuthority("admin", "user")
                                .requestMatchers("/relatorios/**").hasAnyAuthority("admin", "user")
                                .anyRequest().authenticated()
                )
                .userDetailsService(funcionarioService)
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/autenticacao/login")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .addLogoutHandler((request, response, authentication) -> {
                        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                        response.setHeader("Pragma", "no-cache");
                        response.setHeader("Expires", "0");
                    })
                )
                .build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
