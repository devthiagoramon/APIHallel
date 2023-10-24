package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntradaFinanceiraResponseUltimas {
    private String descricaoEntrada;
    private Double valorEntrada;
}
