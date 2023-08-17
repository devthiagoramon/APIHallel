package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
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

    public CartaoAssociado toCartaoAssociado() {
        CartaoAssociado cartaoAssociado = new CartaoAssociado();
        cartaoAssociado.setNumeroCartao(getNumCartao());
        cartaoAssociado.setCvc(getCvcCartao());
        cartaoAssociado.setDataValidadeCartao(getDataValidadeCartao());
        cartaoAssociado.setEndereco(getNome());
        cartaoAssociado.setNomeTitular(getEmail());
        return cartaoAssociado;
    }

    public Associado toAssociado(){
        Associado associado = new Associado();
        associado.setNome(getNome());
        associado.setCpf(getCpf());
        associado.setDataNascimento(getDataNascimento());
        associado.setTelefone(getTelefone());
        return associado;
    }
}
