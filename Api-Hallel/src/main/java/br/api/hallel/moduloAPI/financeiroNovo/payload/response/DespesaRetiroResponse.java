package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;
import lombok.Data;

@Data
public class DespesaRetiroResponse extends SaidaFinanceiroResponse{
    public DespesaRetiroResponse toDespesaResponseList(DespesaRetiro despesaRetiro){
        if(despesaRetiro == null){
            return null;
        }
        DespesaRetiroResponse response = new DespesaRetiroResponse();
        response.setId(despesaRetiro.getId());
        response.setCodigo(despesaRetiro.getCodigo());
        response.setData(despesaRetiro.getData());
        response.setValor(despesaRetiro.getValor());
        response.setMetodoPagamento(despesaRetiro.getMetodoPagamento());

        return response;
    }
}
