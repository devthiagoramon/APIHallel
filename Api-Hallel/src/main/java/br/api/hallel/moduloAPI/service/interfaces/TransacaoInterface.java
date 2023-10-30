package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.requerimento.TransacaoRequerimento;

public interface TransacaoInterface {

    Transacao transacao(TransacaoRequerimento transacaoRequerimento);
    Associado createAssociado(Associado associado);

}
