package com.bluehouse.bluehouse.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

    @Email(message = "O email inválido")
    private String email;
    @NotBlank(message = "Senha Errada")
    private String senha;    
}

