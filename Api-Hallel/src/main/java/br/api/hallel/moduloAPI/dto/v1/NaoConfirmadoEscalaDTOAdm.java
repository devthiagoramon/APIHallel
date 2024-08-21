package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NaoConfirmadoEscalaDTOAdm {
    private String idMembroMinisterio;
    private String motivo;
}
