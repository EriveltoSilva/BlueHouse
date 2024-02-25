package com.bluehouse.bluehouse.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.bluehouse.bluehouse.models.ocorrencias.AutoNoticiaModel;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluehouse.bluehouse.models.Converter.RoleConverter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "funcionario")
public class FuncionarioModel extends PessoaModel implements UserDetails{
    
    @NotBlank(message = "O cargo não pode estar em branco")
    private String cargo;

    @NotBlank(message = "O departamento não pode estar em branco")
    private String departamento;

    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "O email deve conter '@' e '.'.")
    @NotBlank
    private String email;

    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    // @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?~\\/-]).*$", message = "A senha deve conter pelo menos uma letra maiúscula e um caracter especial.")
    private String senha;

    @Column(nullable = false)
    @Convert(converter = RoleConverter.class)
    private Role role;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AutoNoticiaModel> autoNoticias = new ArrayList<AutoNoticiaModel>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getDescricao());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setUsername(String email){
        this.email = email;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return getNomeCompleto();
    }
}
