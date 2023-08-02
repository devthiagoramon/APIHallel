package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import lombok.Data;

import java.util.List;

@Data
public class PagamentoAssociadoResponse extends EntradaFinanceiroResponse{
    private List<Associado> para;
    private String idAssociadoPagador;

    public PagamentoAssociadoResponse toPagamentoResponseList(PagamentosAssociado pagamentosAssociado){
        if(pagamentosAssociado == null){
            return null;
        }
        PagamentoAssociadoResponse response = new PagamentoAssociadoResponse();
        response.setId(pagamentosAssociado.getId());
        response.setCodigo(pagamentosAssociado.getCodigo());
        response.setData(pagamentosAssociado.getData());
        response.setValor(pagamentosAssociado.getValor());
        response.setMetodoPagamento(pagamentosAssociado.getMetodoPagamento());
        response.setPara(pagamentosAssociado.getPara());
        response.setIdAssociadoPagador(pagamentosAssociado.getIdAssociadoPagador());
        return response;
    }
}
