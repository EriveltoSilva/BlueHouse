package com.bluehouse.bluehouse.DTO;

import com.bluehouse.bluehouse.models.ReportanteModel;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportanteDTO {
    private UUID id;
    private String nomeCompleto;
    private Date dataNascimento;
    private String bi;
    private String endereco;
    private String contacto;
    private String genero;

    public static ReportanteDTO fromReportante(ReportanteModel reportante) {
        ReportanteDTO dto = new ReportanteDTO();
        dto.setId(reportante.getId());
        dto.setNomeCompleto(reportante.getNomeCompleto());
        dto.setDataNascimento(reportante.getDataNascimento());
        dto.setBi(reportante.getBi());
        dto.setEndereco(reportante.getEndereco());
        dto.setContacto(reportante.getContacto());
        dto.setGenero(reportante.getGenero());
        return dto;
    }
}
