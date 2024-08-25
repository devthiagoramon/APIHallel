package br.api.hallel.integrationtests.ministerio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
