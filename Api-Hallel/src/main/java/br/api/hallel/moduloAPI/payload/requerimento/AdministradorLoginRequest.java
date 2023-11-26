package br.api.hallel.moduloAPI.payload.requerimento;

import lombok.Data;

@Data
public class AdministradorLoginRequest {
    private String email;
    private String senha;
}
