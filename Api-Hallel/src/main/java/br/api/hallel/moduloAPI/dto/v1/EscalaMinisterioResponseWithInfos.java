package br.api.hallel.moduloAPI.dto.v1;

import br.api.hallel.moduloAPI.model.MembroMinisterio;
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
    private EventosShortResponse eventoId;
    private Date date;
    private List<MembroMinisterioWithUserInfosResponse> membrosMinisterioConvidados;
    private List<MembroMinisterioWithUserInfosResponse> membrosMinisterioConfirmados;
    private List<NaoConfirmadoEscalaMinisterioWithInfosResponse> membroMinisterioNaoConfirmados;

}
