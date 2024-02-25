package com.bluehouse.bluehouse.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import com.bluehouse.bluehouse.models.MedidaDisciplinarModel;

public interface MedidaDisciplinarRepository extends JpaRepository<MedidaDisciplinarModel, UUID>  {
    List<MedidaDisciplinarModel> findByFuncionarioAndDataInicioBeforeAndDataTerminoAfter(
        FuncionarioModel funcionario, Date dataInicio, Date dataTermino);
    List<MedidaDisciplinarModel> findByFuncionario_NomeCompleto(String nomeCompleto);
}
