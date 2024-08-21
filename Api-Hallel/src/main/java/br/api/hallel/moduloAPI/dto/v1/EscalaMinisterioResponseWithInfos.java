package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EscalaMinisterioResponseWithInfos {

    private String id;
    private MinisterioResponse ministerio;
    private EventosShortResponse evento;
    private Date date;
    private List<String> membrosMinisterioConvidadosIds;
    private List<String> membrosMinisterioConfimadoIds;
    private List<String> membrosMinisterioNaoConfirmadoIds;

}
