package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Role;
import br.api.hallel.moduloAPI.model.StatusMembro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembroResponse {

    private String id;
    private String nome;
    private String email;
    private StatusMembro statusMembro;
    private Set<Role> roles;


    public MembroResponse toList(Membro membro) {
        MembroResponse response = new MembroResponse();
        response.setNome(membro.getNome());
        response.setEmail(membro.getEmail());
        response.setStatusMembro(membro.getStatusMembro());
        return response;
    }

    public MembroResponse toResponse(Membro membro) {
        MembroResponse response = new MembroResponse();
        response.setNome(membro.getNome());
        response.setEmail(membro.getEmail());
        response.setRoles(membro.getRoles());
        return response;
    }

}
