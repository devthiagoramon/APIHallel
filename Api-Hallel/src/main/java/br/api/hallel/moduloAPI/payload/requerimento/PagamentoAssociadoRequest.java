package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoAssociadoRequest {
    private CodigoEntradaFinanceiro codigo;
    private Date date;
    private double valor;
    private Integer metodoPagamentoNum;
    private List<Associado> para; // Opcional
    private String idAssociado;

    public PagamentosAssociado toPagamentoAssociado() {
        PagamentosAssociado pagamentoAssociado = new PagamentosAssociado();
        pagamentoAssociado.setCodigo(this.codigo);
        pagamentoAssociado.setDate(new Date());
        pagamentoAssociado.setValor((double) 25.00);

        switch (metodoPagamentoNum){
            case 1:
                pagamentoAssociado.setMetodoPagamento(MetodosPagamentosFinanceiro.PIX);
                break;
            case 2:
                pagamentoAssociado.setMetodoPagamento(MetodosPagamentosFinanceiro.CARTAO_MAQUINA);
                break;
            case 3:
                pagamentoAssociado.setMetodoPagamento(MetodosPagamentosFinanceiro.CARTAO_DEBITO);
                break;
            case 4:
                pagamentoAssociado.setMetodoPagamento(MetodosPagamentosFinanceiro.CARTAO_CREDITO);
                break;
        }
        pagamentoAssociado.setPara(this.para);
        // Isso só ocorre se o associado já estiver cadastro no BD
        if(this.idAssociado != null) {
            pagamentoAssociado.setIdAssociadoPagador(this.idAssociado);
        }
        return pagamentoAssociado;
    }

}
