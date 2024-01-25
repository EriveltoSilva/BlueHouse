package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return funcionarioRepository.save(novoFuncionario);
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
        return user;
    }

    /*public List<FuncionarioModel> obterTodosFuncionariosExcetoAdmin() {
        return funcionarioRepository.findByRoleNot("admin");
    }*/
}
