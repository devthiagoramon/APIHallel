package br.api.hallel.moduloAPI.dto.v1;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MinisterioResponse {
    private String id;
    private String nome;
    private String coordenadorId;
    private String viceCoordenadorId;

}
