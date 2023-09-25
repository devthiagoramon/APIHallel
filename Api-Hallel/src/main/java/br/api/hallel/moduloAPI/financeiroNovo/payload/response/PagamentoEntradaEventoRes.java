package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.Eventos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntradaEventoRes extends EntradaFinanceiroResponse {

    private StatusEntradaEvento status;
    private String emailMembroPagador;
    private String idEventos;
    private List<Eventos> eventosList;

    public PagamentoEntradaEventoRes toPagamentoEntradaEventoRes(PagamentoEntradaEvento pagamento){

        PagamentoEntradaEventoRes response = new PagamentoEntradaEventoRes();
        response.setId(pagamento.getId());
        response.setCodigo(pagamento.getCodigo());
        response.setValor(pagamento.getValor());
        response.setData(pagamento.getDate());
        response.setMetodoPagamento(pagamento.getMetodoPagamento());
        response.setStatus(getStatus());
        response.setIdEventos(pagamento.getEmailMembroPagador());
        response.setEmailMembroPagador(pagamento.getIdEventos());

        return response;
    }
    public PagamentoEntradaEventoReq toPagamentoEntradaReq(PagamentoEntradaEventoRes pagamentoResponse){

        PagamentoEntradaEventoReq request = new PagamentoEntradaEventoReq();
        request.setId(pagamentoResponse.getId());
        request.setCodigo(pagamentoResponse.getCodigo());
        request.setDate(pagamentoResponse.getData());
        request.setValor(pagamentoResponse.getValor());
        request.setMetodoPagamento(pagamentoResponse.getMetodoPagamento());
        request.setEmail(pagamentoResponse.getEmailMembroPagador());
        request.setIdEvento(pagamentoResponse.getIdEventos());
        request.setStatus(pagamentoResponse.getStatus());

        return request;
    }
}
