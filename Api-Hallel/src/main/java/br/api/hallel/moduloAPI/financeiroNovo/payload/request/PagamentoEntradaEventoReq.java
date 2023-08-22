package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PagamentoEntradaEventoReq extends EntradasFinanceiro {

    @Nullable
    @NotBlank
    private StatusEntradaEvento status;
    @Nullable
    @NotBlank
    private String idMembroPagador;
    @Nullable
    @NotBlank
    private String idEvento;


    public PagamentoEntradaEvento toPagamentoEntradaEvento() {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();
        pagamento.setCodigo(getCodigo());
        pagamento.setDate(getDate());
        pagamento.setMetodoPagamento(getMetodoPagamento());
        pagamento.setValor(getValor());
        pagamento.setStatusEntrada(getStatus());
        pagamento.setIdMembroPagador(getIdMembroPagador());
        pagamento.setIdEvento(getIdEvento());

        return pagamento;
    }

    public PagamentoEntradaEvento toPag(PagamentoEntradaEvento pagamentoNew) {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();

        pagamento.setCodigo(pagamentoNew.getCodigo());
        pagamento.setDate(pagamentoNew.getDate());
        pagamento.setMetodoPagamento(pagamentoNew.getMetodoPagamento());
        pagamento.setValor(pagamentoNew.getValor());
        pagamento.setStatusEntrada(pagamentoNew.getStatusEntrada());
        pagamento.setIdMembroPagador(pagamentoNew.getIdMembroPagador());
        pagamento.setIdEvento(pagamentoNew.getIdEvento());
        return pagamento;
    }

}
