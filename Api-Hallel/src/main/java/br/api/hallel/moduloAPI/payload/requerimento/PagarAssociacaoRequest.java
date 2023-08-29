package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.CartaoAssociado;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagarAssociacaoRequest {

    private String idAssociado;
    private String email;
    private int numMetodoPagamento;
    @Nullable
    private CartaoAssociado cartaoAssociado;
    private String mes;
    private String ano;

    public PagarAssociacaoRequest pagarRequest(){
        PagarAssociacaoRequest pagarRequest = new PagarAssociacaoRequest();
        pagarRequest.setIdAssociado(this.getIdAssociado());
        pagarRequest.setNumMetodoPagamento(this.getNumMetodoPagamento());
        pagarRequest.setCartaoAssociado(this.getCartaoAssociado());
        pagarRequest.setAno(this.getAno());
        pagarRequest.setMes(this.getMes());
        return pagarRequest;
    }

    public PagarAssociacaoRequest pagarParaAlguem(){
        PagarAssociacaoRequest pagarRequest = new PagarAssociacaoRequest();
        pagarRequest.setEmail(this.getEmail());
        pagarRequest.setNumMetodoPagamento(this.getNumMetodoPagamento());
        pagarRequest.setCartaoAssociado(this.getCartaoAssociado());
        return pagarRequest;
    }

}
