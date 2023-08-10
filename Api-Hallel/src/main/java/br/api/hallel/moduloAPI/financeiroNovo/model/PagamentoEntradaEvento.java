package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.model.Membro;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private String idEvento;
    private String idMembroPagador;
    private List<Eventos> eventosList;
}
