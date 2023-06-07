package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;

import java.util.List;

public interface SorteioInterface {
    Sorteio createSorteio(SorteioRequest sorteio);
    List<SorteioResponse> listAllSorteio();
    SorteioResponse listSorteioById(String idSorteio);
    SorteioResponse updateSorteioById(String idSorteio, SorteioRequest sorteioRequest);
    void deleteSorteioById(String idSorteio);
}
