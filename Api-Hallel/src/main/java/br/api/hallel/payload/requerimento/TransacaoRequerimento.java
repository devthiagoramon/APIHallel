package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Associado;
import br.api.hallel.model.MetodoPagamento;
import br.api.hallel.model.Transacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TransacaoRequerimento {

    @NotNull(message = "O nome deve ser preenchido")
    @NotBlank(message = "O nome deve ser preenchido")
    private String nomeTransacao;
    private String data;
    @NotNull(message = "O método de pagamento deve ser preenchido")
    @NotBlank(message = "O método de pagamento deve ser preenchido")
    private MetodoPagamento metodoPagamento;
    @DecimalMin(value = "50.0", message = "Valor da pago, não condiz com a da mensalidade")
    private Double mensalidade;
    @NotNull(message = "Deve ser informado se o Associado pagou")
    @NotBlank(message = "Deve ser informado se o Associado pagou")
    private Boolean pago;
    @NotNull(message = "Deve ser informado o Associado")
    @NotBlank(message = "Deve ser informado o Associado ")
    private Associado associado;

    public Transacao toTransacao(){
        Transacao transacao = new Transacao();
        transacao.setNomeTransacao(this.getNomeTransacao());
        transacao.setMensalidade(this.getMensalidade());
        transacao.setMetodoPagamento(this.getMetodoPagamento());
        transacao.setAssociado(this.getAssociado());

        return transacao;
    }

}
