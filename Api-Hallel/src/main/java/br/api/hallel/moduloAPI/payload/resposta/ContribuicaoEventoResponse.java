package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoEventoResponse {
    private String emailPagador;
    private String tipoContribuicao;
    private Integer quantidade;
    private EventosRequest eventos;
    public ContribuicaoEventoResponse toResponse(ContribuicaoEvento conEvento){
        ContribuicaoEventoResponse response = new ContribuicaoEventoResponse();
        response.setEmailPagador(conEvento.getEmailContribuidor());
        response.setTipoContribuicao(conEvento.getTipoContribuicao());
        response.setQuantidade(conEvento.getQuantidade());
        response.setEventos(conEvento.getEventos());
        return response;
    }

}
