package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.DoacaoDinheiroEvento;
import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventoDoacoesResponse {
    private String idEvento;
    private String nomeEvento;
    private Date dataEvento;
    private List<DoacaoObjetosEventos> doacoesObjetos;
    private List<DoacaoDinheiroEvento> doacoesDinheiro;
    private Integer totalDeDoacoes;

}
