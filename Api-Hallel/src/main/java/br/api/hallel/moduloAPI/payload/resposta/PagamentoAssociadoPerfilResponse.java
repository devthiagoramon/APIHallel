package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.financeiroNovo.model.MetodosPagamentosFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoAssociadoPerfilResponse {

    private Date date;
    private MetodosPagamentosFinanceiro metodoPag;
    private double valor;

    private Date dateToPagar;

    public PagamentoAssociadoPerfilResponse(PagamentosAssociado pagamentosAssociado){
        this.date = pagamentosAssociado.getDate();
        this.metodoPag = pagamentosAssociado.getMetodoPagamento();
        this.valor = pagamentosAssociado.getValor();
        this.dateToPagar = pagamentosAssociado.getDataPaga();
    }

}
