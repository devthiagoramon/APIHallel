package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.ContribuicaoEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoEventoReq {

    private String nome;
    private String emailPagador;
    private String tipoContribuicao;
    private Integer quantidade;
    private EventosRequest eventos;
    private Date data;

    public ContribuicaoEvento toContribuicaoEvento() {
        ContribuicaoEvento request = new ContribuicaoEvento();
        request.setNome(getNome());
        request.setEmailPagador(getEmailPagador());
        request.setTipoContribuicao(getTipoContribuicao());
        request.setQuantidade(getQuantidade());
        request.setEvento(getEventos());
        request.setData(getData());
        return request;
    }

}
