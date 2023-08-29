package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoAssociadoResponse;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.CartaoAssociado;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.requerimento.PagarAssociacaoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.VirarAssociadoRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPerfilResponse;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.payload.resposta.PagamentoAssociadoPerfilResponse;

import java.util.Date;
import java.util.List;

public interface AssociadoInterface {

    List<Associado> listAllAssociado();
    List<AssociadoResponseList> listAllAssociadoByMesAno(String mes, String ano);
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
    Boolean pagarAssociacao(PagarAssociacaoRequest pagarAssociacaoRequest);
    Boolean pagarAlguemAssociado(PagarAssociacaoRequest pagarAssociacaoRequest);
    Boolean criarAssociado(VirarAssociadoRequest virarAssociadoRequest);
    List<Date> listarDatasPagas(String idAssociado);
    PagamentoAssociadoResponse listarPagamentoByMesAno(String idAssociado, String mes, String ano);
    AssociadoPerfilResponse visualizarPerfilAssociado(String idAssociado);
    PagamentoAssociadoPerfilResponse listarPagamentoPerfilByMesAno(String idAssociado, String mes, String ano);
    CartaoAssociado cartaoAssociado(String idAssociado);
}
