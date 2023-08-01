package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoAssociadoRequest {
    private CodigoEntradaFinanceiro codigo;
    private Date data;
    private double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;
    private List<Associado> para; // Opcional
    private String idAssociado;

    public PagamentosAssociado toPagamentoAssociado() {
        PagamentosAssociado pagamentoAssociado = new PagamentosAssociado();
        pagamentoAssociado.setCodigo(this.codigo);
        pagamentoAssociado.setData(this.data);
        pagamentoAssociado.setValor(this.valor);
        pagamentoAssociado.setMetodoPagamento(this.metodoPagamento);
        pagamentoAssociado.setPara(this.para);
        pagamentoAssociado.setIdAssociadoPagador(this.idAssociado);
        return pagamentoAssociado;
    }

}
