package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AssociadoRole;
import lombok.Data;

@Data
public class AssociadoReq {

    private String nome;
    private String email;
    private String dataNascimento;
    private AssociadoRole isAssociado;

    public Associado toAssociado(){
        Associado associado = new Associado();
        associado.setNome(this.getNome());
        associado.setEmail(this.getEmail());
        associado.setDataNascimentoAssociado(this.dataNascimento);
        associado.setIsAssociado(this.getIsAssociado());

        return associado;
    }

}
