package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EditCoordMinisterioDTO {
    private String coordenadorId;
    private String viceCoordenadorId;
}
