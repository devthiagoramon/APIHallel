package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.RecompensaRequest;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoSorteioResponse;
import br.api.hallel.moduloAPI.payload.resposta.PerfilAssociadoSorteiosResponse;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;

import java.util.List;

public interface SorteioInterface {
    Sorteio createSorteio(SorteioRequest sorteio);
    List<SorteioResponse> listAllSorteio();
    SorteioResponse listSorteioById(String idSorteio);
    SorteioResponse updateSorteioById(String idSorteio, SorteioRequest sorteioRequest);
    void deleteSorteioById(String idSorteio);
    AssociadoSorteioResponse realizarSorteio(String idSorteio, RecompensaRequest recompensa);

    List<Associado> listAssociadosSorteadoByDate(String mes, String ano);

    SorteioResponse adicionarAssociadoAoSorteio();

    List<PerfilAssociadoSorteiosResponse> listAllSorteioPerfilAssociado(String mes, String ano);
}
