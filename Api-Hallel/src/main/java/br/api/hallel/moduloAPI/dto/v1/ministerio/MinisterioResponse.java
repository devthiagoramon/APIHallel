package br.api.hallel.moduloAPI.dto.v1.ministerio;

import lombok.*;

import java.util.List;

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
    private String descricao;
    private String imagem;
    private List<String> objetivos;
}
