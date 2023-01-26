package br.api.hallel.payload.resposta;

import br.api.hallel.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdministradorResponse {

    private String id;
    private String nome;
    private Set<Role> roles;
}
