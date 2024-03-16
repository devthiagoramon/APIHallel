package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.CartaoCredito;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InscreverEventoRequest {
    private String idEvento;
    private String id;
    private String email;
    private String cpf;
    private String nome;
    private Integer idade;
    private int numMetodoPagamento;
    private CartaoCredito cartaoCredito;
    private boolean membro;
    private boolean associado;
    private Date dataInscricao;
    private Double ValorPago;

    public InscreverEventoRequest toInscreverEventoRequest(){
        InscreverEventoRequest request = new InscreverEventoRequest();
        request.setIdEvento(getIdEvento());
        request.setEmail(getEmail());
        request.setNumMetodoPagamento(getNumMetodoPagamento());
        return request;
    }

    public Membro toMembroEvento(){
        Membro membroEvento = new Membro();
        membroEvento.setNome(this.getNome());
        membroEvento.setEmail(this.getEmail());
        membroEvento.setCpf(this.getCpf());
        membroEvento.setIdade(this.getIdade());
        membroEvento.setCartaoMembro(this.getCartaoCredito());
        return membroEvento;
    }


}
