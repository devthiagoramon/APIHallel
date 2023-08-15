package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VirarAssociadoRequest {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private String num_cartao;
    private Date data_validade_cartao;
    private Integer cvc_cartao;
    private String nome_titular_cartao;
    private String endereco_cartao;
    private Associado[] para;
    private int metodoPagamentoNum;

    public VirarAssociadoRequest() {
    }

    public CartaoAssociado toCartaoAssociado() {
        CartaoAssociado cartaoAssociado = new CartaoAssociado();
        cartaoAssociado.setNumeroCartao(getNum_cartao());
        cartaoAssociado.setCvc(getCvc_cartao());
        cartaoAssociado.setDataValidadeCartao(getData_validade_cartao());
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
