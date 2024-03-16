package br.api.hallel.moduloAPI.financeiroNovo.model;

import br.api.hallel.moduloAPI.model.CartaoCredito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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


    private Double valorPago;
    private String idEventos;
    private String emailMembroPagador;
    private String nome;
    private Integer idade;
    private String cpf;
    private CartaoCredito cartaoCredito;
    private Date data;

}
