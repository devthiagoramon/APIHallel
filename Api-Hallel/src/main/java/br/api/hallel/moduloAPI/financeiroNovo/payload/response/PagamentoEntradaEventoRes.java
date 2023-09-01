package br.api.hallel.moduloAPI.financeiroNovo.payload.response;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntradaEventoRes extends EntradaFinanceiroResponse {

    private StatusEntradaEvento status;
    private Membro membroPagador;
    private Eventos eventos;
    private List<Eventos> eventosList;

    public PagamentoEntradaEventoRes toPagamentoEntradaEventoRes(PagamentoEntradaEvento pagamento){

        PagamentoEntradaEventoRes response = new PagamentoEntradaEventoRes();
        response.setId(pagamento.getId());
        response.setCodigo(pagamento.getCodigo());
        response.setValor(pagamento.getValor());
        response.setData(pagamento.getDate());
        response.setMetodoPagamento(pagamento.getMetodoPagamento());
        response.setStatus(getStatus());
        response.setMembroPagador(pagamento.getMembro());
        response.setEventos(pagamento.getEventos());

        return response;
    }
    public PagamentoEntradaEventoReq toPagamentoEntradaReq(PagamentoEntradaEventoRes pagamentoResponse){

        PagamentoEntradaEventoReq request = new PagamentoEntradaEventoReq();
        request.setId(pagamentoResponse.getId());
        request.setCodigo(pagamentoResponse.getCodigo());
        request.setDate(pagamentoResponse.getData());
        request.setValor(pagamentoResponse.getValor());
        request.setMetodoPagamento(pagamentoResponse.getMetodoPagamento());
        request.setMembro(pagamentoResponse.getMembroPagador());
        request.setEventos(pagamentoResponse.getEventos());
        request.setStatus(pagamentoResponse.getStatus());

        return request;
    }
}
