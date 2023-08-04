package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.SaidaFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaidaFinanceiroResponse {
    private String id;
    private CodigoSaidaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    public SaidaFinanceiroResponse toDespesaResponseList(SaidaFinanceiro saidaFinanceiro){
        if(saidaFinanceiro == null){
            return null;
        }
        SaidaFinanceiroResponse response = new SaidaFinanceiroResponse();
        response.setId(saidaFinanceiro.getId());
        response.setCodigo(saidaFinanceiro.getCodigo());
        response.setData(saidaFinanceiro.getData());
        response.setValor(saidaFinanceiro.getValor());
        response.setMetodoPagamento(saidaFinanceiro.getMetodoPagamento());

        return response;
    }
}
