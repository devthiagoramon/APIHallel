package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoCredito;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VirarAssociadoRequest {

    private String idMembro;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private String numCartao;
    private Date dataValidadeCartao;
    private Integer cvcCartao;
    private String nomeTitularCartao;
    private String enderecoCartao;
    private int metodoPagamentoNum;

    public VirarAssociadoRequest() {
    }

    public CartaoCredito toCartaoAssociado() {
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setNumeroCartao(getNumCartao());
        cartaoCredito.setCvc(getCvcCartao());
        cartaoCredito.setDataValidadeCartao(getDataValidadeCartao());
        cartaoCredito.setEndereco(getEnderecoCartao());
        cartaoCredito.setNomeTitular(getNomeTitularCartao());
        return cartaoCredito;
    }

    public Associado toAssociado(){
        Associado associado = new Associado();
        associado.setNome(getNome());
        associado.setCpf(getCpf());
        associado.setEmail(getEmail());
        associado.setDataNascimento(getDataNascimento());
        associado.setTelefone(getTelefone());
        return associado;
    }
}
