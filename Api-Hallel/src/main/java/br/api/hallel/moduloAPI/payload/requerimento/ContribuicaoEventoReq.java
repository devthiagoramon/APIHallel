package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import br.api.hallel.moduloAPI.model.Eventos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoEventoReq {

    private String nome;
    private String emailPagador;
    private String tipoContribuicao;
    private Integer quantidade;
    private EventosRequest eventos;
    public ContribuicaoEvento toContribuicaoEvento() {
        ContribuicaoEvento request = new ContribuicaoEvento();
        request.setNome(getNome());
        request.setEmailPagador(getEmailPagador());
        request.setTipoContribuicao(getTipoContribuicao());
        request.setQuantidade(getQuantidade());
        request.setEvento(getEventos());
        return request;
    }

}
