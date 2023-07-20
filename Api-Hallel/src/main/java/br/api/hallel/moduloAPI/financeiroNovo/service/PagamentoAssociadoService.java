package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.financeiroNovo.repository.PagamentoAssociadoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.PagamentoAssociadoRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PagamentoAssociadoService implements MetodosCRUDFinanceiro<PagamentosAssociado, PagamentoAssociadoRequest> {

    @Autowired
    private PagamentoAssociadoRepository pagamentoAssociadoRepository;

    @Override
    public Boolean cadastrar(PagamentoAssociadoRequest request) {
        PagamentosAssociado pagamentosAssociado = this.pagamentoAssociadoRepository.insert(request.toPagamentoAssociado());
        log.info("Pagamento associado (id: "+pagamentosAssociado.getId()+") salvo no sistema");
        return true;
    }

    @Override
    public Boolean editar(String id, PagamentoAssociadoRequest request) {
        Optional<PagamentosAssociado> optional = this.pagamentoAssociadoRepository.findById(id);
        if(optional.isPresent()){
            PagamentosAssociado pagamentosAssociadoOld = optional.get();
            pagamentosAssociadoOld.setCodigo(request.getCodigo());
            pagamentosAssociadoOld.setData(request.getData());
            pagamentosAssociadoOld.setValor(request.getValor());
            pagamentosAssociadoOld.setPara(request.getPara());
            pagamentosAssociadoOld.setIdAssociadoPagador(request.getIdAssociado());
            pagamentosAssociadoOld.setMetodoPagamento(request.getMetodoPagamento());
            log.info("Pagamento associado (id: " + id + ") alterado com sucesso");
            return true;
        }else{
            log.info("Pagamento associado (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.pagamentoAssociadoRepository.deleteById(id);
        log.info("Pagamento assoociado (id: "+id+") deletado com sucesso");
        return true;
    }

    @Override
    public List<PagamentosAssociado> listarAll() {
        return this.pagamentoAssociadoRepository.findAll();
    }

    @Override
    public PagamentosAssociado listarPorId(String id) {
        Optional<PagamentosAssociado> optional = this.pagamentoAssociadoRepository.findById(id);
        return optional.orElse(null);
    }
}
