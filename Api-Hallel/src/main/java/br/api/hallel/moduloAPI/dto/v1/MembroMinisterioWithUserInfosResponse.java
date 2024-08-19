package br.api.hallel.moduloAPI.dto.v1;

import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;

import java.util.List;

public class MembroMinisterioWithUserInfosResponse {
    private String id;
    private MembroResponse membro;
    private String ministerioId;
    private List<FuncaoMinisterio> funcaoMinisterios;
}
