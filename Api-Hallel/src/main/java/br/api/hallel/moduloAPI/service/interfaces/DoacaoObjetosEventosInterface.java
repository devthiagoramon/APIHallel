package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoObjetosEventosReq;
import br.api.hallel.moduloAPI.payload.resposta.DoacaoObjetosEventosResponse;

import java.util.List;

public interface DoacaoObjetosEventosInterface {

    DoacaoObjetosEventos createObjeto(DoacaoObjetosEventosReq req);

    List<DoacaoObjetosEventosResponse> listAllObjetos();

    DoacaoObjetosEventosResponse ListDoacaoObjetosEventosById(String Id);

    DoacaoObjetosEventosResponse updateDoacaoObjetosEventosById(String Id, DoacaoObjetosEventosReq doacaoObjetosEventosReq);

    void deleteDoacaoObjetosEventosById(String Id);

    void deleteDoacaoObjetosEventosByObj(DoacaoObjetosEventosReq doacaoObjetosEventosReq);


}
