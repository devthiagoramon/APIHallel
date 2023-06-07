package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoacaoReq;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.moduloAPI.payload.resposta.DoacoesObjetoListaAdmResponse;

import java.util.List;

public interface DoacaoInterface {

    Doacao doar(DoacaoReq doacaoReq);
    List<DoacoesDinheiroListaAdmResponse> listAllDoacoes();
    Doacao listDoacaoById(String id);

    List<DoacoesObjetoListaAdmResponse> listAllDoacoesObjeto();

    DoacaoObjeto doarObjeto(DoacaoObjetoReq doacaoObjeto);

    DoacaoObjeto objetoRecebido(String id);

    DoacaoObjeto objetoNaoRecebido(String id);

    DoacaoObjeto listDoacaoObjetoById(String id);

}
