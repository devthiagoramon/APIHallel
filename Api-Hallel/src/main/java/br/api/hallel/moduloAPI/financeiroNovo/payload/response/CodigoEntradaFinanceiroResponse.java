package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import lombok.Data;

@Data
public class CodigoEntradaFinanceiroResponse {

    private String id;

    public CodigoEntradaFinanceiroResponse toResponseList(CodigoEntradaFinanceiro codigo){
        if(codigo == null){
            return null;
        }
        CodigoEntradaFinanceiroResponse response = new CodigoEntradaFinanceiroResponse();
        response.setId(codigo.getId());
        return response;
    }
}
