package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements UserDetailsService{
    
    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioModel criar(FuncionarioModel novoFuncionario) {
        novoFuncionario.setPassword(new BCryptPasswordEncoder().encode(novoFuncionario.getPassword()));
        return funcionarioRepository.save(novoFuncionario);
    }

    public List<FuncionarioModel> listar() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel editar(FuncionarioModel novoFuncionario) {
        if (novoFuncionario.getPassword() != null && !novoFuncionario.getPassword().isEmpty()) {
            novoFuncionario.setPassword(new BCryptPasswordEncoder().encode(novoFuncionario.getPassword()));
        }

        FuncionarioModel updatedFuncionario = funcionarioRepository.save(novoFuncionario);
        UserDetails updatedUserDetails = funcionarioRepository.findByEmail(updatedFuncionario.getEmail());
        authenticateUserAndSetContext(updatedUserDetails);

        return updatedFuncionario;
    }
    
    private void authenticateUserAndSetContext(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public Optional<FuncionarioModel> obterFuncionarioModel(UUID id)
    {
        return funcionarioRepository.findById(id);
    }

    public void eliminar( UUID id ) 
    {
        funcionarioRepository.deleteById(id);;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = funcionarioRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        return user;
    }
}
