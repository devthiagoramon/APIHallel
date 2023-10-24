package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.CartaoCredito;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private String nome;
    private Integer idade;
    private String cpf;
    private CartaoCredito cartaoCredito;

}
