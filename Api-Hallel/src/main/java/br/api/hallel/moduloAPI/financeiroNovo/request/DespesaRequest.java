package br.api.hallel.moduloAPI.financeiroNovo.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoSaidaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DespesaRequest {
    private CodigoSaidaFinanceiro codigo;
    private Date data;
    private Double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

}
