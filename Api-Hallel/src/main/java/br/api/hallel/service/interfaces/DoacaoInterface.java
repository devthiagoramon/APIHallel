package br.api.hallel.service.interfaces;

import br.api.hallel.model.Doacao;
import br.api.hallel.payload.requerimento.DoacaoReq;

import java.util.List;

public interface DoacaoInterface {

    public Doacao doar(DoacaoReq doacaoReq);


}
