package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import jakarta.annotation.Nullable;
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
    @Nullable
    private Date data;
    @Nullable
    private double valor;
    private MetodosPagamentosFinanceiro metodoPagamento;

    @Nullable
    private List<Associado> para; // Opcional
    @Nullable
    private String idAssociado;

    public PagamentosAssociado toPagamentoAssociado() {
        PagamentosAssociado pagamentoAssociado = new PagamentosAssociado();
        pagamentoAssociado.setCodigo(this.codigo);
        pagamentoAssociado.setData(new Date());
        pagamentoAssociado.setValor((double) 25.00);
        pagamentoAssociado.setMetodoPagamento(this.metodoPagamento);
        pagamentoAssociado.setPara(this.para);
        // Isso só ocorre se o associado já estiver cadastro no BD
        if(this.idAssociado != null) {
            pagamentoAssociado.setIdAssociadoPagador(this.idAssociado);
        }
        return pagamentoAssociado;
    }

}
