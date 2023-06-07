package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Role;
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
    private Set<Role> roles;



}
