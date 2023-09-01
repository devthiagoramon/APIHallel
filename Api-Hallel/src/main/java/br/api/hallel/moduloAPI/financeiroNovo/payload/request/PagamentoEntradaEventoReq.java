package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntradaEventoReq extends EntradasFinanceiro {

    @Nullable
    @NotBlank
    private StatusEntradaEvento status;
    @Nullable
    @NotBlank
    private Eventos eventos;
    @Nullable
    @NotBlank
    private Membro membro;
    private CartaoAssociado cartaoAssociado;

    public PagamentoEntradaEvento toPagamentoEntradaEvento() {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();
        pagamento.setCodigo(getCodigo());
        pagamento.setDate(getDate());
        pagamento.setMetodoPagamento(getMetodoPagamento());
        pagamento.setValor(getValor());
        pagamento.setStatusEntrada(getStatus());
        pagamento.setMembro(getMembro());
        pagamento.setEventos(getEventos());
        pagamento.setCartaoAssociado(getCartaoAssociado());
        return pagamento;
    }

    public PagamentoEntradaEvento toPag(PagamentoEntradaEvento pagamentoNew) {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();

        pagamento.setCodigo(pagamentoNew.getCodigo());
        pagamento.setDate(pagamentoNew.getDate());
        pagamento.setMetodoPagamento(pagamentoNew.getMetodoPagamento());
        pagamento.setValor(pagamentoNew.getValor());
        pagamento.setStatusEntrada(pagamentoNew.getStatusEntrada());
        pagamento.setMembro(pagamentoNew.getMembro());
        pagamento.setEventos(pagamentoNew.getEventos());
        return pagamento;
    }

}
