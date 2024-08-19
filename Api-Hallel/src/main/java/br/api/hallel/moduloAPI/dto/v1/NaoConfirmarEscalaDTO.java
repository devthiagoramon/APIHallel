package br.api.hallel.moduloAPI.dto.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NaoConfirmarEscalaDTO {
    @NotNull(message = "Insira o id do membro ministerio")
    @NotBlank(message = "Insira o id do membro ministerio")
    private String idMembroMinisterio;
    @NotNull(message = "Insira o id da escala ministerio")
    @NotBlank(message = "Insira o id da escala ministerio")
    private String idEscalaMinisterio;
    @NotNull(message = "Insira o motivo da ausencia")
    @NotBlank(message = "Insira o motivo da ausencia")
    private String motivo;
}
