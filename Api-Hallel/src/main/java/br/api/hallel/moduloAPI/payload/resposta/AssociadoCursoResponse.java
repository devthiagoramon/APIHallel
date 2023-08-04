package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoStatus;
import br.api.hallel.moduloAPI.model.StatusMembro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoCursoResponse {
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
        associadoVal.setStatus(associado.getStatus());

        return associadoVal;
    }
}
