package br.api.hallel.service.interfaces;

import br.api.hallel.payload.requerimento.RecompensaRequest;
import br.api.hallel.payload.resposta.AssociadoResponse;
import br.api.hallel.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.payload.resposta.SorteioResponse;

public interface RecompensaInterface {
    SorteioResponse addToSort(String idSorteio, String idAssociado);
    AssociadoSorteioResponse sendRecompensa(String idSorteio, RecompensaRequest recompensa);
}
