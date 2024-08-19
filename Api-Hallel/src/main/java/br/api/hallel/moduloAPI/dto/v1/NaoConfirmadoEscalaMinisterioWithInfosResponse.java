package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NaoConfirmadoEscalaMinisterioWithInfosResponse {
    private String id;
    private MembroMinisterioWithUserInfosResponse membroMinisterio;
    private String idEscalaMinisterio;
    private String motivo;
}
