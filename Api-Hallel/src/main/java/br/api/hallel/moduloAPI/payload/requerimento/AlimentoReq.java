package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlimentoReq {

    private String nomeAlimento;
    private String tipo;
    private String dataValidade;
    private Integer quantidade;
    private Double peso;
//    private Membro membro;



    public AlimentoReq toAlimentoReq(Alimentos alimentos) {
        AlimentoReq req = new AlimentoReq();
        req.setNomeAlimento(alimentos.getNomeAlimento());
        req.setTipo(alimentos.getTipo());
        req.setPeso(alimentos.getPeso());
        req.setQuantidade(alimentos.getQuantidade());
        req.setDataValidade(alimentos.getDataValidade());

        return req;
    }

    public Alimentos toAlimentos() {

        return new Alimentos(getTipo(), getDataValidade(), getNomeAlimento(), getQuantidade(), getPeso());
    }

}
