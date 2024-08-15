package br.api.hallel.moduloAPI.dto.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinisterioDTO {

    @NotNull
    @NotBlank(message = "Digite um nome para o ministerio!")
    private String nome;
    private String coordenadorId;
    private String viceCoordenadorId;
}
