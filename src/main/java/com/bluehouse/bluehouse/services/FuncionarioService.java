package com.bluehouse.bluehouse.services;

import com.bluehouse.bluehouse.DTO.FuncionarioDTO;
import com.bluehouse.bluehouse.DTO.ReportanteDTO;
import com.bluehouse.bluehouse.controllers.MedidaDisciplinarController;
import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.MedidaDisciplinarModel;
import com.bluehouse.bluehouse.models.ReportanteModel;
import com.bluehouse.bluehouse.repositories.FuncionarioRepository;
import com.bluehouse.bluehouse.repositories.MedidaDisciplinarRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements UserDetailsService{
    
    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    private final MedidaDisciplinarRepository medidaDisciplinarRepository;

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
        // Verificar se o funcionário tem uma medida disciplinar ativa
        if (hasMedidaDisciplinarAtiva(user)) {
            throw new DisabledException("A conta está desativada devido a uma medida disciplinar.");
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        return user;
    }

    public List<FuncionarioDTO> buscarPorNome(String nomeCompleto) {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto);
        return funcionarios.stream()
                .map(FuncionarioDTO::fromFuncionario)
                .collect(Collectors.toList());
    }

    private boolean hasMedidaDisciplinarAtiva(UserDetails user) {
        if (user instanceof FuncionarioModel) {
            FuncionarioModel funcionario = (FuncionarioModel) user;
            // Obter todas as medidas disciplinares ativas para o funcionário
            List<MedidaDisciplinarModel> medidasAtivas = medidaDisciplinarRepository
                    .findByFuncionarioAndDataInicioBeforeAndDataTerminoAfter(funcionario,
                            new Date(), new Date());
    
            // Verificar se há alguma medida disciplinar ativa
            return !medidasAtivas.isEmpty();
        } else {
            return false;
        }
    }

    public long getTotal() {
        return funcionarioRepository.count(); // Isso conta o número total 
    }
    
}
