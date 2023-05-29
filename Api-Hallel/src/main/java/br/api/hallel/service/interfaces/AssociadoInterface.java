package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.resposta.AssociadoPagamentosRes;

import java.util.List;

public interface AssociadoInterface {

    List<Associado> listAllAssociado();
    Associado listAssociadoById(String id);
    void deleteAssociado(String id);

    Associado updateAssociadoById(String id, Associado associado);

    List<AssociadoPagamentosRes> getAllPagamentosAssociados();

    AssociadoPagamentosRes getAssociadoPagamentoById(String id);

    Associado findByEmail(String email);
    List<Associado> listAssociadosByPago();
    List<Associado> listAssociadosByPendente();
    List<Associado> listAssociadosByNaoPago();
    List<Transacao> listPagamentoCredito();
    List<Transacao> listPagamentoDebito();
    List<Transacao> listPagamentoDinheiro();
}
