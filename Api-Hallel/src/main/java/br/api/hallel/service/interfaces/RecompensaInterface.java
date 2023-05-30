package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Sorteio;
import br.api.hallel.payload.resposta.RecompensaResponse;

import java.util.List;

public interface RecompensaInterface {
    List<Sorteio> addToSort(String idSorteio, String idAssociado);
    Associado sendRecompensa(String idSorteio,RecompensaResponse recompensa);
}
