package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoAssociadoResponse;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoAssociadoRepository;
import br.api.hallel.moduloAPI.payload.requerimento.PagamentoAssociadoRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PagamentoAssociadoService implements MetodosCRUDFinanceiro<PagamentosAssociado, PagamentoAssociadoRequest, PagamentoAssociadoResponse> {

    @Autowired
    private PagamentoAssociadoRepository pagamentoAssociadoRepository;

    @Override
    public PagamentosAssociado cadastrar(PagamentoAssociadoRequest request) {
        PagamentosAssociado pagamentosAssociado = this.pagamentoAssociadoRepository.insert(request.toPagamentoAssociado());
        log.info("Pagamento associado (id: " + pagamentosAssociado.getId() + ") salvo no sistema");
        return pagamentosAssociado;
    }

    @Override
    public Boolean editar(String id, PagamentoAssociadoRequest request) {
        Optional<PagamentosAssociado> optional = this.pagamentoAssociadoRepository.findById(id);
        if (optional.isPresent()) {
            PagamentosAssociado pagamentosAssociadoOld = optional.get();
            pagamentosAssociadoOld.setCodigo(request.getCodigo());
            pagamentosAssociadoOld.setDate(request.getDate());
            pagamentosAssociadoOld.setValor(request.getValor());
            pagamentosAssociadoOld.setPara(request.getPara());
            pagamentosAssociadoOld.setIdAssociadoPagador(request.getIdAssociado());
            pagamentosAssociadoOld.setMetodoPagamento(request.toPagamentoAssociado().getMetodoPagamento());
            this.pagamentoAssociadoRepository.save(pagamentosAssociadoOld);
            log.info("Pagamento associado (id: " + id + ") alterado com sucesso");
            return true;
        } else {
            log.info("Pagamento associado (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.pagamentoAssociadoRepository.deleteById(id);
        log.info("Pagamento assoociado (id: " + id + ") deletado com sucesso");
        return true;
    }

    @Override
    public List<PagamentoAssociadoResponse> listarAll() {
        List<PagamentoAssociadoResponse> responseList = new ArrayList<>();
        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll()) {
            responseList.add(new PagamentoAssociadoResponse().toPagamentoResponseList(pagamentosAssociado));
        }
        return responseList;
    }

    @Override
    public List<PagamentoAssociadoResponse> listByPage(int pagina) {
        Pageable pageable = PageRequest.of(pagina,15);
        List<PagamentoAssociadoResponse> responseList = new ArrayList<>();
        for (PagamentosAssociado pagamentosAssociado : this.pagamentoAssociadoRepository.findAll(pageable)) {
            responseList.add(new PagamentoAssociadoResponse().toPagamentoResponseList(pagamentosAssociado));
        }
        return responseList;
    }

    @Override
    public PagamentoAssociadoResponse listarPorId(String id) {
        Optional<PagamentosAssociado> optional = this.pagamentoAssociadoRepository.findById(id);
        return new PagamentoAssociadoResponse().toPagamentoResponseList(optional.orElse(null));
    }
}
