package br.api.hallel.integrationtests.ministerio.dto;

import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinisterioWithCoordsResponse {
    private String id;
    private String nome;
    private MembroResponse coordenador;
    private MembroResponse viceCoordenador;
}
