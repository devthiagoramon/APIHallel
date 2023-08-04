package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaFinanceiroResponse {
    private String id;
    private CodigoEntradaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    public EntradaFinanceiroResponse toResponseList(EntradasFinanceiro entradasFinanceiro){
        if(entradasFinanceiro == null){
            return null;
        }
        EntradaFinanceiroResponse response = new EntradaFinanceiroResponse();
        response.setId(entradasFinanceiro.getId());
        response.setCodigo(entradasFinanceiro.getCodigo());
        response.setData(entradasFinanceiro.getData());
        response.setValor(entradasFinanceiro.getValor());
        response.setMetodoPagamento(entradasFinanceiro.getMetodoPagamento());

        return response;
    }

}
