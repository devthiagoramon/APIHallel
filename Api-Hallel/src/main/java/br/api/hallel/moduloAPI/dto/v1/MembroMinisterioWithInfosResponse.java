package br.api.hallel.moduloAPI.dto.v1;

import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MembroMinisterioWithInfosResponse {
    private String id;
    private MembroResponse membro;
    private MinisterioResponse ministerio;
    private List<FuncaoMinisterio> funcaoMinisterio;
}
