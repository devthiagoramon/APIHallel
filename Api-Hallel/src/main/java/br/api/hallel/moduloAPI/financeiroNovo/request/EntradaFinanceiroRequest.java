package br.api.hallel.moduloAPI.financeiroNovo.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntradaFinanceiroRequest {
    private CodigoEntradaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    public EntradasFinanceiro toEntradaFinanceiro(){
        EntradasFinanceiro entradasFinanceiro = new EntradasFinanceiro();
        entradasFinanceiro.setCodigo(this.getCodigo());
        entradasFinanceiro.setData(this.getData());
        entradasFinanceiro.setValor(this.getValor());
        entradasFinanceiro.setMetodoPagamento(this.getMetodoPagamento());
        return entradasFinanceiro;
    }
}
