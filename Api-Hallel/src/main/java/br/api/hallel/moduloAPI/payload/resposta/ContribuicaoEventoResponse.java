package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import br.api.hallel.moduloAPI.payload.requerimento.EventosRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoEventoResponse {
    private String nome;
    private String emailPagador;
    private String tipoContribuicao;
    private Integer quantidade;
    private EventosRequest eventos;
    public ContribuicaoEventoResponse toResponse(ContribuicaoEvento conEvento){
        ContribuicaoEventoResponse response = new ContribuicaoEventoResponse();
        response.setNome(conEvento.getNome());
        response.setEmailPagador(conEvento.getEmailPagador());
        response.setTipoContribuicao(conEvento.getTipoContribuicao());
        response.setQuantidade(conEvento.getQuantidade());
        response.setEventos(conEvento.getEvento());
        return response;
    }

}
