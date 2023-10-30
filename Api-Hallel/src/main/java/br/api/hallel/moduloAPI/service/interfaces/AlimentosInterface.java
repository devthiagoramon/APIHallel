package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Alimentos;
import br.api.hallel.moduloAPI.payload.requerimento.AlimentoReq;
import br.api.hallel.moduloAPI.payload.resposta.AlimentoResponse;

import java.util.List;

public interface AlimentosInterface {
    Alimentos createAlimento(AlimentoReq req);

    List<AlimentoResponse> listAllAlimentos();

    AlimentoResponse listAlimentoById(String id);

    AlimentoResponse updateAlimentoById(String id, AlimentoReq alimentoReq);

    void deleteAlimentoById(String id);

    void deleteAlimentoByObj(AlimentoReq alimentoReq);
}
