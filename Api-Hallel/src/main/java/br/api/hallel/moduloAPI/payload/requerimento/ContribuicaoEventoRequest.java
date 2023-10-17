package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoEventoRequest {

    private String emailContribuicao;

    private String tipoContribuicao;

    private Integer quantidade;

    private EventosRequest eventos;

    private Date date;

    public ContribuicaoEvento toContribuicaoEvento() {
        ContribuicaoEvento request = new ContribuicaoEvento();
        request.setEmailContribuidor(getEmailContribuicao());
        request.setTipoContribuicao(getTipoContribuicao());
        request.setQuantidade(getQuantidade());
        request.setDate(getDate());
        request.setEventos(eventos);
        return request;
    }

}
