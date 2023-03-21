package br.api.hallel.service.interfaces;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;

import java.util.List;

public interface DoacaoInterface {

    Doacao doar(DoacaoReq doacaoReq);
    List<Doacao> listAllDoacoes();
    Doacao listDoacaoById(String id);

}
