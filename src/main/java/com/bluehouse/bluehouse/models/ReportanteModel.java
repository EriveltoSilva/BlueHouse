package com.bluehouse.bluehouse.models;

import com.bluehouse.bluehouse.models.ocorrencias.DenunciaModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "reportante")
public class ReportanteModel  extends PessoaModel{

    @OneToMany(mappedBy = "reportante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DenunciaModel> denuncias = new ArrayList<DenunciaModel>();
}
