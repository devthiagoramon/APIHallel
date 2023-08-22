package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import lombok.Data;

@Data
public class DespesaEventoResponse extends SaidaFinanceiroResponse {
    public DespesaEventoResponse toDespesaResponseList(DespesaEvento despesaEvento){
        if(despesaEvento == null){
            return null;
        }
        DespesaEventoResponse response = new DespesaEventoResponse();
        response.setId(despesaEvento.getId());
        response.setCodigo(despesaEvento.getCodigo());
        response.setData(despesaEvento.getData());
        response.setValor(despesaEvento.getValor());
        response.setMetodoPagamento(despesaEvento.getMetodoPagamento());

        return response;
    }
}
