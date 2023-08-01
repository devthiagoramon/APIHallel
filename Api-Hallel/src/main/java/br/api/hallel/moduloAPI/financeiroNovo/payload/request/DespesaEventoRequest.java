package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;

public class DespesaEventoRequest extends DespesaRequest{
    public DespesaEvento toDespesaEvento() {
        DespesaEvento despesaEvento = new DespesaEvento();
        despesaEvento.setCodigo(getCodigo());
        despesaEvento.setData(getData());
        despesaEvento.setValor(getValor());
        despesaEvento.setMetodoPagamento(getMetodoPagamento());
        return despesaEvento;
    }
}
