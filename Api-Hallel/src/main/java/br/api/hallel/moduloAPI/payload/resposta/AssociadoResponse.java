package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Recompensa;
import br.api.hallel.moduloAPI.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoResponse {

    private String id;
    private String nome;
    private String email;
    private List<Recompensa> recompensas;
    private Set<Role> roles;

}