package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaRetiro;
import br.api.hallel.moduloAPI.financeiroNovo.payload.request.DespesaRetiroRequest;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesasRetiroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DespesaRetiroService implements MetodosCRUDFinanceiro<DespesaRetiro, DespesaRetiroRequest> {

    @Autowired
    private DespesasRetiroRepository despesasRetiroRepository;

    @Override
    public Boolean cadastrar(DespesaRetiroRequest request) {
        DespesaRetiro despesaRetiro = this.despesasRetiroRepository.insert(request.toDespesaRetiro());
        log.info("Despesa Retiro (id: " + despesaRetiro.getId() + ") adicionada com sucesso");
        return true;
    }

    @Override
    public Boolean editar(String id, DespesaRetiroRequest request) {
        Optional<DespesaRetiro> optional = this.despesasRetiroRepository.findById(id);
        if (optional.isPresent()) {
            DespesaRetiro despesaRetiroOld = optional.get();
            despesaRetiroOld.setCodigo(request.getCodigo());
            despesaRetiroOld.setData(request.getData());
            despesaRetiroOld.setValor(request.getValor());
            despesaRetiroOld.setMetodoPagamento(request.getMetodoPagamento());
            log.info("Despesa Retiro (id: " + id + ") alterada com sucesso");
            return true;
        } else {
            log.info("Despesa Retiro (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.despesasRetiroRepository.deleteById(id);
        log.info("Despesa retiro (id: " + id + ") deletada com sucesso");
        return true;
    }

    @Override
    public List<DespesaRetiro> listarAll() {
        return this.despesasRetiroRepository.findAll();
    }

    @Override
    public DespesaRetiro listarPorId(String id) {
        Optional<DespesaRetiro> optional = this.despesasRetiroRepository.findById(id);
        return optional.orElse(null);
    }
}
