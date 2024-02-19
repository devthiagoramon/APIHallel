package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.DoacaoDinheiroEvento;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoDinheiroEventoReq;
import br.api.hallel.moduloAPI.payload.resposta.DoacaoDinheiroEventoResponse;

import java.util.List;


public interface DoacaoDinheiroEventoInterface {

    DoacaoDinheiroEvento createObjeto(DoacaoDinheiroEventoReq req);


    List<DoacaoDinheiroEventoResponse> listAllObjetos();

    DoacaoDinheiroEventoResponse ListDoacaoObjetosEventosById(String Id);

    DoacaoDinheiroEventoResponse updateDoacaoObjetosEventosById(String Id, DoacaoDinheiroEventoReq doacaoDinheiroEventosReq);

    void deleteDoacaoObjetosEventosById(String Id);

    void deleteDoacaoObjetosEventosByObj(DoacaoDinheiroEventoReq doacaoDinheiroEventosReq);


}
