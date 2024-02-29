package br.api.hallel.moduloAPI.financeiroNovo.payload.request;

import br.api.hallel.moduloAPI.financeiroNovo.model.EntradasFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.model.CartaoCredito;
import br.api.hallel.moduloAPI.payload.requerimento.InscreverEventoRequest;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntradaEventoReq extends EntradasFinanceiro {

    @Nullable
    @NotBlank
    private StatusEntradaEvento status;
    @Nullable
    @NotBlank
    private String idEvento;
    @Nullable
    @NotBlank
    private String email;
    private String nome;
    private Integer idade;
    private String cpf;
    private CartaoCredito cartaoCredito;
    private Date dataPagamento;

    public PagamentoEntradaEvento toPagamentoEntradaEvento() {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();
        pagamento.setCodigo(getCodigo());
        pagamento.setDate(getDate());
        pagamento.setMetodoPagamento(getMetodoPagamento());
        pagamento.setValor(getValor());
        pagamento.setStatusEntrada(getStatus());
        pagamento.setIdEventos(getIdEvento());
        pagamento.setEmailMembroPagador(getEmail());
        pagamento.setCartaoCredito(getCartaoCredito());
        pagamento.setNome(getNome());
        pagamento.setCpf(getCpf());
        pagamento.setIdade(getIdade());
        pagamento.setDate(getDataPagamento());
        return pagamento;
    }

    public PagamentoEntradaEventoReq toPagamentoEntradaEventoReq(InscreverEventoRequest inscreverEventoRequest){
        PagamentoEntradaEventoReq req = new PagamentoEntradaEventoReq();
        req.setIdEvento(inscreverEventoRequest.getIdEvento());
        req.setEmail(inscreverEventoRequest.getEmail());
        req.setNome(inscreverEventoRequest.getNome());
        req.setIdade(inscreverEventoRequest.getIdade());
        req.setCpf(inscreverEventoRequest.getCpf());
        req.setCartaoCredito(inscreverEventoRequest.getCartaoCredito());
        req.setDataPagamento(inscreverEventoRequest.getDataInscricao());
        return req;
    }


    public PagamentoEntradaEvento toPag(PagamentoEntradaEvento pagamentoNew) {
        PagamentoEntradaEvento pagamento = new PagamentoEntradaEvento();

        pagamento.setCodigo(pagamentoNew.getCodigo());
        pagamento.setDate(pagamentoNew.getDate());
        pagamento.setMetodoPagamento(pagamentoNew.getMetodoPagamento());
        pagamento.setValor(pagamentoNew.getValor());
        pagamento.setStatusEntrada(pagamentoNew.getStatusEntrada());
        pagamento.setEmailMembroPagador(pagamentoNew.getEmailMembroPagador());
        pagamento.setIdEventos(pagamentoNew.getIdEventos());
        pagamento.setNome(pagamento.getNome());
        pagamento.setCpf(pagamento.getCpf());
        pagamento.setIdade(pagamento.getIdade());

        return pagamento;
    }

}
