package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoCredito;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoUsuarioVerifyResponse {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private Integer idade;

    private CartaoCredito cartaoCredito;
    private boolean isMembro;
    private boolean isAssociado;

    public EventoUsuarioVerifyResponse toEventoUsuarioVerifyResponse(Object object){
        if(object instanceof Associado associado){
            setId(associado.getId());
            setNome(associado.getNome());
            setCpf(associado.getCpf());
            setIdade(associado.getIdade());
            setEmail(associado.getEmail());
            setCartaoCredito(associado.getCartaoCredito());
            setAssociado(true);
        }else if (object instanceof Membro membro){
            setId(membro.getId());
            setNome(membro.getNome());
            setEmail(membro.getEmail());
            setCpf(membro.getCpf());
            setIdade(membro.getIdade());
            setCartaoCredito(null);
            setMembro(true);
        }
        return this;
    }

}
