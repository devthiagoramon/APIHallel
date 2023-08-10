package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

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
    private List<Eventos> eventosList;

    public PagamentoEntradaEvento toPagamentoEntradaEvento() {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();
        pagamento.setCodigo(getCodigo());
        pagamento.setDate(getDate());
        pagamento.setMetodoPagamento(getMetodoPagamento());
        pagamento.setValor(getValor());
        pagamento.setStatusEntrada(getStatus());
        pagamento.setIdMembroPagador(getIdMembroPagador());
        pagamento.setEventosList(getEventosList());
        pagamento.setIdEvento(getIdEvento());

        return pagamento;
    }

}
