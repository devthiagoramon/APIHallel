package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinisterioResponse {
    private String id;
    private String nome;
    private String coordenadorId;
    private String viceCoordenadorId;

}
