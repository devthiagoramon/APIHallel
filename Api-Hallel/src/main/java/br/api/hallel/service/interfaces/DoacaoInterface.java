package br.api.hallel.service.interfaces;

import br.api.hallel.model.Doacao;
import br.api.hallel.model.DoacaoObjeto;
import br.api.hallel.payload.requerimento.DoacaoObjetoReq;
import br.api.hallel.payload.requerimento.DoacaoReq;
import br.api.hallel.payload.resposta.DoacoesDinheiroListaAdmResponse;
import br.api.hallel.payload.resposta.DoacoesObjetoListaAdmResponse;

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
