package br.api.hallel.moduloAPI.dto.v1;

import br.api.hallel.moduloAPI.model.Eventos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EscalaMinisterioWithEventoInfoResponse {

    private String id;
    private EventosShortResponse evento;
    private String ministerioId;
    private Date date;

}
