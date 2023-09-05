package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.EventoInscritoReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@ToString
public class PagamentoEntradaEvento extends EntradasFinanceiro {

    /*
    *Confirmando = pagamento recebido, entrada confirmada.
    * Não confirmado = pagamento não recebido, entrada não confirmada.
    * Andamento = pagamento em andamento.
     */

    public PagamentoEntradaEvento(){
        setStatusEntrada(StatusEntradaEvento.ANDAMENTO);
    }

    private StatusEntradaEvento statusEntrada;
    private String idEventos;
    private String emailMembroPagador;
    private CartaoAssociado cartaoAssociado;

}
