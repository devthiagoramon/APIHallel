package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;

public interface RecompensaInterface {
    SorteioResponse addToSort(String idSorteio, String idAssociado);

}
