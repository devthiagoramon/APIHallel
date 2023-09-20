package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.PagamentoEntradaEventoReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscreverEventoRequest {
    private String idEvento;
    private String emailMembroPagador;
    private String cpf;
    private String nome;
    private Integer idade;
    private int numMetodoPagamento;
    private Integer mes;
    private Integer ano;
    private PagamentoEntradaEventoReq pagamentoEntradaEvento;

    public InscreverEventoRequest toInscreverEventoRequest(){
        InscreverEventoRequest request = new InscreverEventoRequest();
        request.setIdEvento(getIdEvento());
        request.setEmailMembroPagador(getEmailMembroPagador());
        request.setNumMetodoPagamento(getNumMetodoPagamento());
        request.setMes(getMes());
        request.setAno(getAno());
        request.setPagamentoEntradaEvento(getPagamentoEntradaEvento());
        return request;
    }

}
