package br.api.hallel.moduloAPI.payload.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitasDiaAtualResponse {
    private String dia;
    private double valorTotal;
}
