package br.api.hallel.moduloAPI.dto.v1.ministerio;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EscalaMinisterioWithEventoInfoResponse {

    private String id;
    private EventosShortResponse evento;
    private String ministerioId;
    private Date date;

}
