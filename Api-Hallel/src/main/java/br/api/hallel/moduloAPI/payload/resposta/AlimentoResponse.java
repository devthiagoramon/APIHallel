package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Alimentos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoResponse {
    private String id;
    private String tipo;
    private String dataValidade;
    private String nomeAlimento;
    private Integer quantidade;
    private Double peso;

    public AlimentoResponse toResponse(Alimentos alimentos) {
        return new AlimentoResponse(alimentos.getId(), alimentos.getTipo(), alimentos.getDataValidade(),
                alimentos.getNomeAlimento(), alimentos.getQuantidade(), alimentos.getPeso());
    }

}
