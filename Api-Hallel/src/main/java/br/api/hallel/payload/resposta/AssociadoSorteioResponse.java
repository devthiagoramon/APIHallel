package br.api.hallel.payload.resposta;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Recompensa;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AssociadoSorteioResponse {

    private String id;
    private String nome;
    private String email;
    private List<Recompensa> recompensas;

    public AssociadoSorteioResponse toResponse(Associado associado){
        AssociadoSorteioResponse response = new AssociadoSorteioResponse();

        response.setId(associado.getId());
        response.setNome(associado.getNome());
        response.setEmail(associado.getEmail());
        response.setRecompensas(associado.getRecompensas());
        return response;
    }
}
