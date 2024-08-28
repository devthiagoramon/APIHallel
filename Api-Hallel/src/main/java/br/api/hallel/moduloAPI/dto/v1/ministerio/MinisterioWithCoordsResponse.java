package br.api.hallel.moduloAPI.dto.v1.ministerio;

import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinisterioWithCoordsResponse {
    private String id;
    private String nome;
    private MembroResponse coordenador;
    private MembroResponse viceCoordenador;
    private String descricao;
    private String imagem;
    private List<String> objetivos;
}
