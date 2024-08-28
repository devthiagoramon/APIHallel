package br.api.hallel.moduloAPI.dto.v1.ministerio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinisterioDTO {

    @NotNull
    @NotBlank(message = "Digite um nome para o ministerio!")
    private String nome;
    private String descricao;
    private String imagem;
    private List<String> objetivos;
    private String coordenadorId;
    private String viceCoordenadorId;

    public MinisterioDTO(String nome, String coordenadorId,
                         String viceCoordenadorId) {
        this.nome = nome;
        this.coordenadorId = coordenadorId;
        this.viceCoordenadorId = viceCoordenadorId;
    }
}
