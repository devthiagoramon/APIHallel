package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.LocalEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalEventoLocalizacaoResponse {

    private String localizacao;

    public LocalEventoLocalizacaoResponse(LocalEvento localEvento){
        this.localizacao = localEvento.getLocalizacao();
    }
}
