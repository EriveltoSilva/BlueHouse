package com.bluehouse.bluehouse.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bluehouse.bluehouse.dto.FuncionarioDto;
import com.bluehouse.bluehouse.entities.Funcionario;
import com.bluehouse.bluehouse.http.ResponseBody;
import com.bluehouse.bluehouse.http.ResponseControllerUtils;
import com.bluehouse.bluehouse.services.FuncionarioService;

@Controller
public class FuncionarioController extends ResponseControllerUtils{
 
    @Autowired
    private FuncionarioService service;

    @GetMapping("funcionarios/listar")
    public ResponseEntity<ResponseBody> listar() 
    {
        return this.ok("Pessoas Listadas com sucesso.", this.service.findAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> obter(@PathVariable UUID id) 
    {
        Optional<Funcionario> funcionario = this.service.findById(id);
        if ( funcionario.isPresent() )
            return this.ok("Funcionario encontrado com sucesso.", funcionario.get());
        return this.naoEncontrado("Funcionario não encontrado", null);
    }

    @PostMapping("funcionarios/cadastrar")
    public ResponseEntity<ResponseBody> criar(@RequestBody FuncionarioDto funcionarioDto) {
        Funcionario funcionario = new Funcionario.FuncionarioBuilder()
                    .nome(funcionarioDto.getNome())
                    .dataNascimento(funcionarioDto.getDataNascimento())
                    .cargo(funcionarioDto.getCargo())
                    .departamento(funcionarioDto.getDepartamento())
                    .email(funcionarioDto.getEmail())
                    .senha(funcionarioDto.getSenha())
                    .endereco(funcionarioDto.getEndereco())
                    .build();
        return this.created("Funcionario adicionada com sucesso.", this.service.criar(funcionario) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> eliminar(@PathVariable("id") UUID id) {
        return this.ok("Funcionario eliminada com sucesso.", this.service.eliminar(id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> editar(@PathVariable("id") UUID id, @RequestBody FuncionarioDto funcionarioDto) {
         Funcionario funcionario = new Funcionario.FuncionarioBuilder()
                    .nome(funcionarioDto.getNome())
                    .dataNascimento(funcionarioDto.getDataNascimento())
                    .cargo(funcionarioDto.getCargo())
                    .departamento(funcionarioDto.getDepartamento())
                    .email(funcionarioDto.getEmail())
                    .senha(funcionarioDto.getSenha())
                    .endereco(funcionarioDto.getEndereco())
                    .build();
        return this.ok("Funcionario editada com sucesso.", (Funcionario) service.editar(id, funcionario));
    }
}
