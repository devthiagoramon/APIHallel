package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import lombok.Data;

@Data
public class CodigoSaidaFinanceiroResponse {
    private String id;

    public CodigoSaidaFinanceiroResponse toResponseList(CodigoSaidaFinanceiro saida){
        if(saida == null){
            return null;
        }
        CodigoSaidaFinanceiroResponse response = new CodigoSaidaFinanceiroResponse();
        response.setId(saida.getId());
        return response;
    }
}
