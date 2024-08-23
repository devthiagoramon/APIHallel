package br.api.hallel.moduloAPI.dto.v1.ministerio;

import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// DTO With membro info
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NaoConfirmadoEscalaMinisterioWithInfos {
    private String id;
    private MembroResponse membro;
    private String idEscalaMinisterio;
    private String motivo;
}
