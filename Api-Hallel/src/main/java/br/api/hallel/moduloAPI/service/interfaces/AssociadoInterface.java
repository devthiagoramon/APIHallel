package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;

import java.util.List;

public interface AssociadoInterface {

    List<Associado> listAllAssociado();
    List<Associado> listAllAssociadoByMesAnoPagos(String mes, String ano);
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

    Boolean pagarAssociacao(String idAssociado);
}
