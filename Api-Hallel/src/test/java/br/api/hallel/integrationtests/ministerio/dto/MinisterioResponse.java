package br.api.hallel.integrationtests.ministerio.dto;

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
