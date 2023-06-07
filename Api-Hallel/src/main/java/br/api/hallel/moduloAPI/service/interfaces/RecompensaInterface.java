package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.payload.requerimento.RecompensaRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;

public interface RecompensaInterface {
    SorteioResponse addToSort(String idSorteio, String idAssociado);
    AssociadoSorteioResponse sendRecompensa(String idSorteio, RecompensaRequest recompensa);
}
