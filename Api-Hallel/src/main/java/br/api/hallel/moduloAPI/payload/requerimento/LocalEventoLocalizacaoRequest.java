package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.LocalEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalEventoLocalizacaoRequest {

    private String id;
    private String localizacao;

    public LocalEventoLocalizacaoRequest toRequest(LocalEvento localEvento){
        LocalEventoLocalizacaoRequest request = new LocalEventoLocalizacaoRequest();
        request.setId(localEvento.getId());
        request.setLocalizacao(localEvento.getLocalizacao());
        return request;
    }

}
