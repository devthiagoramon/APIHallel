package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaidaFinanceiroRequest {

    private CodigoSaidaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    public SaidaFinanceiro toSaidaFinanceiro(){
        SaidaFinanceiro saidaFinanceiro = new SaidaFinanceiro();
        saidaFinanceiro.setCodigo(this.getCodigo());
        saidaFinanceiro.setDate(this.getData());
        saidaFinanceiro.setValor(this.getValor());
        saidaFinanceiro.setMetodoPagamento(this.getMetodoPagamento());
        return saidaFinanceiro;
    }
}
