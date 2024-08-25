package br.api.hallel.moduloAPI.payload.requerimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorLoginRequest {
    private String email;
    private String senha;
}
