package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaidaFinanceiraResponseUltimas {
    private String descricaoSaida;
    private Double valorSaida;
}
