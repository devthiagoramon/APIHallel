package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PagamentoEntradaEvento extends EntradasFinanceiro {

    /*
    *Confirmando = pagamento recebido, entrada confirmada.
    * Não confirmado = pagamento não recebido, entrada não confirmada.
    * Andamento = pagamento em andamento.
     */

    private StatusEntradaEvento statusEntrada;
    private Eventos eventos;
    private Membro membro;
    private CartaoAssociado cartaoAssociado;
}
