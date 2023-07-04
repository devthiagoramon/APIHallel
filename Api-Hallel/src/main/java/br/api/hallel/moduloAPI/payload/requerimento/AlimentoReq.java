package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Alimentos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoReq {
    private String tipo;
    private String dataValidade;
    private String nomeAlimento;
    private Integer quantidade;
    private Double peso;

    public Alimentos toAlimentos(){

        return new Alimentos(getTipo(), getDataValidade(), getNomeAlimento(), getQuantidade(), getPeso());
    }

}
