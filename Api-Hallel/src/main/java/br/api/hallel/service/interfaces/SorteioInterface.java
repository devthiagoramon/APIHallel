package br.api.hallel.service.interfaces;

import br.api.hallel.model.Sorteio;
import br.api.hallel.payload.requerimento.SorteioRequest;
import br.api.hallel.payload.resposta.SorteioResponse;

import java.util.List;

public interface SorteioInterface {
    Sorteio createSorteio(SorteioRequest sorteio);
    List<SorteioResponse> listAllSorteio();
    SorteioResponse listSorteioById(String idSorteio);
    SorteioResponse updateSorteioById(String idSorteio, SorteioRequest sorteioRequest);
    void deleteSorteioById(String idSorteio);
}
