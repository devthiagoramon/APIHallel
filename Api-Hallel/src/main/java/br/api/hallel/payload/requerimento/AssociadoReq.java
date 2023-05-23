package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AssociadoRole;
import br.api.hallel.model.StatusMembro;
import lombok.Data;

@Data
public class AssociadoReq {

    private String id;
    private String nome;
    private String email;
    private String dataNascimentoAssociado;
    private AssociadoRole isAssociado;
    private Boolean isPago;
    private StatusMembro status;


    public Associado toAssociado(Associado associado) {
        Associado associadoVal = new Associado();

        associadoVal.setId(associado.getId());
        associadoVal.setNome(associado.getNome());
        associadoVal.setEmail(associado.getEmail());
        associadoVal.setDataNascimentoAssociado(associado.getDataNascimentoAssociado());
        associadoVal.setIsAssociado(associado.getIsAssociado());
        associadoVal.setIsPago(associado.getIsPago());
        associadoVal.setStatus(associado.getStatus());

        return associadoVal;
    }

}
