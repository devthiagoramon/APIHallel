package br.api.hallel.payload.resposta;

import br.api.hallel.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoResponse {

    private String id;
    private String nome;
    private String email;
    private Set<Role> roles;

}
