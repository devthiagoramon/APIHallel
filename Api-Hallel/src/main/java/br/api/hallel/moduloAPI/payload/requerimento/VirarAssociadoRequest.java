package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirarAssociadoRequest {

    private AssociadoRequest associadoRequest;
    private Associado para[];
    private int metodoPagamentoNum;

    public CartaoAssociado toCartaoAssociado() {
        CartaoAssociado cartaoAssociado = new CartaoAssociado();
        cartaoAssociado.setNumeroCartao(associadoRequest.getNum_cartao());
        cartaoAssociado.setCvc(associadoRequest.getCvc_cartao());
        cartaoAssociado.setDataValidadeCartao(associadoRequest.getData_validade_cartao());
        cartaoAssociado.setEndereco(associadoRequest.getNome());
        cartaoAssociado.setNomeTitular(associadoRequest.getEmail());
        return cartaoAssociado;
    }

    public Associado toAssociado(){
        Associado associado = new Associado();
        associado.setNome(associadoRequest.getNome());
        associado.setCpf(associadoRequest.getCpf());
        associado.setDataNascimento(associadoRequest.getDataNascimento());
        associado.setTelefone(associadoRequest.getTelefone());
        return associado;
    }
}
