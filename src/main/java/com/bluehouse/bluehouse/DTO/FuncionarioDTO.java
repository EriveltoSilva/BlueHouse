package com.bluehouse.bluehouse.DTO;

import com.bluehouse.bluehouse.models.FuncionarioModel;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {
    private UUID id;
    private String nomeCompleto;
    private String bi;
    private String cargo;
    private String departamento;

    public static FuncionarioDTO fromFuncionario(FuncionarioModel funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNomeCompleto(funcionario.getNomeCompleto());
        dto.setBi(funcionario.getBi());
        dto.setCargo(funcionario.getCargo());
        dto.setDepartamento(funcionario.getDepartamento());
        return dto;
    }
}
