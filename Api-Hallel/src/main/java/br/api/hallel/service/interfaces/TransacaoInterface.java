package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.TransacaoRequerimento;

public interface TransacaoInterface {

    Transacao transacao(TransacaoRequerimento transacaoRequerimento);
    Associado createAssociado(Associado associado);

}
