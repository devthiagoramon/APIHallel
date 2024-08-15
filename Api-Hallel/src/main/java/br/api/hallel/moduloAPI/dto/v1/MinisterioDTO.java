package br.api.hallel.moduloAPI.dto.v1;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinisterioDTO {
    private String nome;
    private String coordenadorId;
    private String viceCoordenadorId;
}
