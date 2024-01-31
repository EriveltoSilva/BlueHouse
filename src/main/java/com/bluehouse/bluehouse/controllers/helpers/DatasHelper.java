package com.bluehouse.bluehouse.controllers.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatasHelper {
    public static List<LocalDate> obterDiasDoMesAtual() {
        List<LocalDate> diasDoMes = new ArrayList<>();

        // Obtenha o primeiro dia do mês atual
        LocalDate primeiroDiaDoMes = LocalDate.now().withDayOfMonth(1);

        // Obtenha o último dia do mês atual
        LocalDate ultimoDiaDoMes = LocalDate.now().withDayOfMonth(primeiroDiaDoMes.lengthOfMonth());

        // Adicione cada dia do mês à lista
        LocalDate diaAtual = primeiroDiaDoMes;
        while (!diaAtual.isAfter(ultimoDiaDoMes)) {
            diasDoMes.add(diaAtual);
            diaAtual = diaAtual.plusDays(1);
        }

        return diasDoMes;
    }
}
