package br.api.hallel.moduloAPI.financeiroNovo.payload.request;


import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;

public class DespesaRetiroRequest extends DespesaRequest{

    public DespesaRetiro toDespesaRetiro() {
        DespesaRetiro despesaRetiro = new DespesaRetiro();
        despesaRetiro.setCodigo(getCodigo());
        despesaRetiro.setDate(getData());
        despesaRetiro.setValor(getValor());
        despesaRetiro.setMetodoPagamento(getMetodoPagamento());
        return despesaRetiro;
    }
}
