package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoStatus;
import br.api.hallel.moduloAPI.model.StatusMembro;
import lombok.Data;

@Data
public class AssociadoReq {

    private String id;
    private String nome;
    private String email;
    private String dataNascimentoAssociado;
    private AssociadoStatus isAssociado;
    private StatusMembro status;


    public Associado toAssociado(Associado associado) {
        Associado associadoVal = new Associado();

        associadoVal.setId(associado.getId());
        associadoVal.setNome(associado.getNome());
        associadoVal.setEmail(associado.getEmail());
        associadoVal.setDataNascimentoAssociado(associado.getDataNascimentoAssociado());
        associadoVal.setIsAssociado(associado.getIsAssociado());
        associadoVal.setStatusMembro(associado.getStatusMembro());

        return associadoVal;
    }

}
