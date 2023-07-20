package br.api.hallel.moduloAPI.financeiroNovo.service;

import br.api.hallel.moduloAPI.financeiroNovo.model.DespesaEvento;
import br.api.hallel.moduloAPI.financeiroNovo.repository.DespesaEventoRepository;
import br.api.hallel.moduloAPI.financeiroNovo.request.DespesaEventoRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DespesaEventoService implements MetodosCRUDFinanceiro<DespesaEvento, DespesaEventoRequest> {

    @Autowired
    private DespesaEventoRepository despesaEventoRepository;

    @Override
    public Boolean cadastrar(DespesaEventoRequest request) {
        DespesaEvento despesaEvento = this.despesaEventoRepository.insert(request.toDespesaEvento());
        log.info("Despesa Evento (id: "+despesaEvento.getId()+") adicionado com sucesso");
        return true;
    }

    @Override
    public Boolean editar(String id, DespesaEventoRequest request) {
        Optional<DespesaEvento> optional = this.despesaEventoRepository.findById(id);
        if (optional.isPresent()){
            DespesaEvento despesaEventoOld = optional.get();
            despesaEventoOld.setCodigo(request.getCodigo());
            despesaEventoOld.setData(request.getData());
            despesaEventoOld.setValor(request.getValor());
            despesaEventoOld.setMetodoPagamento(request.getMetodoPagamento());
            log.info("Despesa Evento (id: " + id + ") alterado com sucesso");
            return true;
        }else{
            log.info("Despesa Evento (id: " + id + ") error na hora de alterar, não encontrado no sistema");
            return false;
        }
    }

    @Override
    public Boolean deletar(String id) {
        this.despesaEventoRepository.deleteById(id);
        log.info("Despesa Evento (id: "+id+") deletado com sucesso");
        return true;
    }

    @Override
    public List<DespesaEvento> listarAll() {
        return this.despesaEventoRepository.findAll();
    }

    @Override
    public DespesaEvento listarPorId(String id) {
        Optional<DespesaEvento> optional = this.despesaEventoRepository.findById(id);
        return optional.orElse(null);
    }
}
