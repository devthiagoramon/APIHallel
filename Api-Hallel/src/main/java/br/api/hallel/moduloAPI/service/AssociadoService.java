package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoStatus;
import br.api.hallel.moduloAPI.model.MetodoPagamento;
import br.api.hallel.moduloAPI.model.Transacao;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.service.interfaces.AssociadoInterface;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository associadoRepository;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    Logger logger = LoggerFactory.getLogger(AssociadoService.class);

    @Override
    public List<Associado> listAllAssociado() {
        return this.associadoRepository.findAll();
    }

    @Override
    public List<AssociadoResponseList> listAllAssociadoByMesAno(String mes, String ano) {
        List<Associado> associadosDB = this.associadoRepository.findAll();
        List<AssociadoResponseList> associadosLoadad = new ArrayList<>();

        for (Associado associado : associadosDB) {
            boolean hasPago = false;
            AssociadoResponseList associadoResponseProv = new AssociadoResponseList();
            associadoResponseProv.setId(associado.getId());
            associadoResponseProv.setNome(associado.getNome());
            if(associado.getMesesPagos()!=null) {
                for (Date mesPagamento : associado.getMesesPagos()) {
                    if (formatter.format(mesPagamento).substring(3).equals(mes + "/" + ano)) {
                        hasPago = true;
                        associadoResponseProv.setStatus(AssociadoStatus.PAGO);
                        associadoResponseProv.setDataPagamento(mesPagamento);
                    }
                }
            }
            if(!hasPago){
                associadoResponseProv.setStatus(AssociadoStatus.NAO_PAGO);
            }
            associadosLoadad.add(associadoResponseProv);
        }
        Collections.sort(associadosLoadad);
        return associadosLoadad;
    }

    //LISTA TODOS OS ASSOCIADOS
    @Override
    public Associado listAssociadoById(String id) {

        logger.info("ASSOCIADO LISTADO!");

        return this.associadoRepository.findById(id).isPresent() ? this.associadoRepository.findById(id).get() : null;
    }

    //DELETA UM ASSOCIADO PELO ID DELE
    @Override
    public void deleteAssociado(String id) {
        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associado = optional.get();

            logger.info("ASSOCIADO REMOVIDO!");

            this.associadoRepository.delete(associado);
        }

    }

    //ATUALIZA INFORMAÇÕES SOBRE O ASSOCIADO
    @Override
    public Associado updateAssociadoById(String id, Associado associado) {

        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associadoOptional = optional.get();

            associadoOptional = associado;

            logger.info("ASSOCIADO ATUALIZADO!");

            return this.associadoRepository.save(associado);
        } else {
            logger.warn("ASSOCIADO NÃO ENCONTRADO!");

            return null;
        }
    }

    @Override
    public List<AssociadoPagamentosRes> getAllPagamentosAssociados() {

        List<Associado> associados = this.associadoRepository.findAll();

        List<AssociadoPagamentosRes> pagamentosRes = new ArrayList<>();

        associados.forEach(associado -> {
            AssociadoPagamentosRes pagamento = new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsPago(), associado.getIsAssociado(), associado.getTransacao());
            pagamentosRes.add(pagamento);
        });

        return pagamentosRes;
    }

    @Override
    public AssociadoPagamentosRes getAssociadoPagamentoById(String id) {
        Associado associado = listAssociadoById(id);

        return new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsPago(), associado.getIsAssociado(), associado.getTransacao());
    }

    @Override
    public Associado findByEmail(String email) {

        Optional<Associado> optional = associadoRepository.findByEmail(email);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    @Override
    public List<Associado> listAssociadosByPago() {

        return this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PAGO).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PAGO) : null;
    }

    @Override
    public List<Associado> listAssociadosByPendente() {

        return associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PENDENTE).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PENDENTE) : null;
    }

    @Override
    public List<Associado> listAssociadosByNaoPago() {
        return this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.NAO_PAGO).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.NAO_PAGO) : null;
    }

    @Override
    public List<Transacao> listPagamentoCredito() {
        List<Transacao> transacaos = new ArrayList<>();
        associadoRepository.findAll().stream().forEach(associado -> {
            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.CARTAO_CREDITO) {
                transacaos.add(associado.getTransacao());
            }
        });
        return transacaos;
    }

    @Override
    public List<Transacao> listPagamentoDebito() {
        List<Transacao> transacaos = new ArrayList<>();
        associadoRepository.findAll().stream().forEach(associado -> {
            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.CARTAO_DEBITO) {
                transacaos.add(associado.getTransacao());
            }
        });
        return transacaos;
    }

    @Override
    public List<Transacao> listPagamentoDinheiro() {
        List<Transacao> transacaoList = new ArrayList<>();
        this.associadoRepository.findAll().stream().forEach(associado -> {
            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.DINHEIRO) {
                transacaoList.add(associado.getTransacao());
            }
        });
        return transacaoList;
    }

    @Override
    public Boolean pagarAssociacao(String idAssociado) {
        Optional<Associado> optional = this.associadoRepository.findById(idAssociado);
        if (optional.isPresent()) {
            Associado associado = optional.get();
            if (associado.getMesesPagos() != null) {
                associado.getMesesPagos().add(new Date());
                this.associadoRepository.save(associado);
            } else {
                ArrayList<Date> datasPagamentosList = new ArrayList<>();
                datasPagamentosList.add(new Date());
                associado.setMesesPagos(datasPagamentosList);
                this.associadoRepository.save(associado);
            }
            return true;
        } else {
            return false;
        }
    }


}
