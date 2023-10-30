package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaEvento {

    private Integer id;
    private String nome;
    private String descricao;
    private TipoDespesa tipoDespesa;

    // Em caso do tipo da despesa for igual a dinheiro
    private double valor;

    // Em caso do tipo da despesa for igual aos demais tipos
    private Integer quantidade;


}
